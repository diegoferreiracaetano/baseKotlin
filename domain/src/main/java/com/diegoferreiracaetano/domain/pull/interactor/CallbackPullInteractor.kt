package com.diegoferreiracaetano.domain.pull.interactor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.observers.DisposableCompletableObserver

class CallbackPullInteractor(private val saveInicialInteractor: SavePullInicialInteractor,
                             private val savePullPageInteractor: SavePullPageInteractor):
        PagedList.BoundaryCallback<Pull>() {

    private var disposable = CompositeDisposable()
    private var retryCompletable: Completable? = null
    private var params = MutableLiveData<Pair<String,String>>()

    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    override fun onZeroItemsLoaded() {
      params.value?.let {
          disposable.add(saveInicialInteractor.execute(SavePullInicialInteractor.Request(it.first,it.second,1))
                  .subscribeWith(object : DisposableCompletableObserver() {
                      override fun onStart() {
                          super.onStart()
                          initialLoad.postValue(NetworkState.LOADING)
                      }

                      override fun onError(t: Throwable) {
                          val erro = NetworkState.error(t.message)
                          initialLoad.postValue(erro)
                          networkState.postValue(erro)
                          setRetry(Action { onZeroItemsLoaded() })
                      }

                      override fun onComplete() {
                          initialLoad.postValue(NetworkState.LOADED)
                          networkState.postValue(NetworkState.LOADED)
                      }
                  }))
      }
    }

    override fun onItemAtEndLoaded(pull: Pull) {
        params.value?.let {
            disposable.add(savePullPageInteractor.execute(SavePullPageInteractor.Request(it.first,it.second, Constants.PAGE_SIZE))
                    .subscribeWith(object : DisposableCompletableObserver() {
                        override fun onStart() {
                            super.onStart()
                            networkState.postValue(NetworkState.LOADING)
                        }

                        override fun onError(t: Throwable) {
                            val erro = NetworkState.error(t.message)
                            networkState.postValue(erro)
                            setRetry(Action { onItemAtEndLoaded(pull) })
                        }

                        override fun onComplete() {
                            networkState.postValue(NetworkState.LOADED)
                        }
                    }))
        }
    }

    fun retry() {
        if (retryCompletable != null) {
            disposable.add(retryCompletable!!
                    .subscribe({ }))
        }
    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
    fun setParam(params: Pair<String,String>){
        this.params.value = params
    }

    fun clear(){
        disposable.clear()
    }

}