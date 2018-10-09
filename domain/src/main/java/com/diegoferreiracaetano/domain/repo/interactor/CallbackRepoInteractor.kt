package com.diegoferreiracaetano.domain.repo.interactor

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.subscribeBy

class CallbackRepoInteractor(private val saveInicialCacheInteractor: SaveInicialCacheInteractor,
                             private val saveCacheInteractor: SaveCacheInteractor): PagedList.BoundaryCallback<Repo>() {

    private var disposable = CompositeDisposable()
    private var retryCompletable: Completable? = null
    val initialLoad = MutableLiveData<NetworkState>()
    val networkState = MutableLiveData<NetworkState>()

    override fun onZeroItemsLoaded() {

        initialLoad.postValue(NetworkState.LOADING)
        networkState.postValue(NetworkState.LOADING)

       saveInicialCacheInteractor.execute(SaveInicialCacheInteractor.Request(1))
                .subscribeBy (
                    onComplete = {
                        initialLoad.postValue(NetworkState.LOADED)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    onError =  {
                        val erro = NetworkState.error(it.message)
                        initialLoad.postValue(erro)
                        networkState.postValue(erro)
                        setRetry(Action { onZeroItemsLoaded() })
                    })
    }


    override fun onItemAtEndLoaded(repo: Repo) {
        networkState.postValue(NetworkState.LOADING)

        saveCacheInteractor.execute(SaveCacheInteractor.Request(Constants.PAGE_SIZE))
                .subscribeBy(
                        onComplete = {
                            networkState.postValue(NetworkState.LOADED)
                        },
                        onError = {
                            val erro = NetworkState.error(it.message)
                            networkState.postValue(erro)
                            setRetry(Action { onItemAtEndLoaded(repo) })
                        })
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

    fun clear(){
        disposable.clear()
    }
}