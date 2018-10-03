package com.diegoferreiracaetano.data.rest

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class DogDataSource(private val compositeDisposable: CompositeDisposable,
                    private val interactor: GetListDogsInteractor): PageKeyedDataSource<Int, Dog>() {


    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()


    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
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


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int,Dog>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)
        compositeDisposable.add(interactor.execute(GetListDogsInteractor.Request(params.requestedLoadSize,0 ))
                .subscribe({ dogs ->
                    networkState.postValue(NetworkState.LOADED)
                    initialLoad.postValue(NetworkState.LOADED)
                    callback.onResult(dogs,0,1)
        }, { throwable ->

                    setRetry(Action { loadInitial(params, callback) })
                    val error = NetworkState.error(throwable.message)
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int,Dog>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(interactor.execute(GetListDogsInteractor.Request(params.requestedLoadSize,params.key)).subscribe({ dogs ->
            setRetry(null)
            networkState.postValue(NetworkState.LOADED)
            callback.onResult(dogs,params.key+1)
        }, { throwable ->

            setRetry(Action { loadAfter(params, callback) })
            val error = NetworkState.error(throwable.message)
            networkState.postValue(error)
        }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int,Dog>) {

    }
}