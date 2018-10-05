package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegoferreiracaetano.data.local.dog.DogBoundaryCallback
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsLocalInteractor
import com.diegoferreiracaetano.domain.dog.interactor.GetTotalInteractor
import com.diegoferreiracaetano.domain.dog.interactor.InsertDogInteractor
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(getListDogsInteractor: GetListDogsInteractor,
                    getListDogsLocalInteractor: GetListDogsLocalInteractor,
                    insertDogInteractor: InsertDogInteractor,
                    getLastIdInteractor: GetTotalInteractor) : ViewModel() {

    val result : LiveData<PagedList<Dog>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 10
    private val callback : DogBoundaryCallback

    init {

        callback = DogBoundaryCallback(compositeDisposable,pageSize,insertDogInteractor,getListDogsInteractor,getLastIdInteractor)

        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()

        result = LivePagedListBuilder<Int, Dog>(getListDogsLocalInteractor.dataSource, config)
                .setBoundaryCallback(callback)
                .build()
    }

    fun retry() {
        callback.retry()
    }

    fun refresh() {
        callback.onZeroItemsLoaded()
    }

    val networkState = callback.networkState
    val initialLoad = callback.initialLoad


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
