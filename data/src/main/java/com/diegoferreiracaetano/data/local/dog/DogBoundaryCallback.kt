package com.diegoferreiracaetano.data.local.dog

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.diegoferreiracaetano.data.NetworkState
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import com.diegoferreiracaetano.domain.dog.interactor.GetTotalInteractor
import com.diegoferreiracaetano.domain.dog.interactor.InsertDogInteractor
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.subscribeBy

class DogBoundaryCallback(private val disposable: CompositeDisposable,
                          private val limit: Int,
                          private val insertDogInteractor: InsertDogInteractor,
                          private val getListDogsInteractor: GetListDogsInteractor,
                          private val getTotalInteractor: GetTotalInteractor): PagedList.BoundaryCallback<Dog>() {

    val networkState = MutableLiveData<NetworkState>()
    val initialLoad = MutableLiveData<NetworkState>()

    private var retryCompletable: Completable? = null

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

    override fun onZeroItemsLoaded() {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        disposable.add(getListDogsInteractor.execute(GetListDogsInteractor.Request(limit,0))
                .flatMapCompletable{insertDogInteractor.execute(InsertDogInteractor.Request(it))}
                .subscribeBy(
                        onComplete = {
                            networkState.postValue(NetworkState.LOADED)
                            initialLoad.postValue(NetworkState.LOADED)
                        },
                        onError = {
                            setRetry(Action { onZeroItemsLoaded() })
                            val error = NetworkState.error(it.message)
                            networkState.postValue(error)
                            initialLoad.postValue(error)
                        }))
    }

    override fun onItemAtEndLoaded(dog: Dog) {
        networkState.postValue(NetworkState.LOADING)
        disposable.add(getTotalInteractor.execute(GetTotalInteractor.Request())
                .map {   Pair(limit,it.div(limit).plus(1)) }
                .toFlowable()
                .flatMap{getListDogsInteractor.execute(GetListDogsInteractor.Request(it.first,it.second))}
                .flatMapCompletable{insertDogInteractor.execute(InsertDogInteractor.Request(it))}
                .subscribeBy(
                        onComplete = {
                            networkState.postValue(NetworkState.LOADED)
                        },
                        onError = {
                            setRetry(Action { onItemAtEndLoaded(dog)})
                            val error = NetworkState.error(it.message)
                            networkState.postValue(error)
                        }))
    }
}