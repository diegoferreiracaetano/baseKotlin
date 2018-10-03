package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegoferreiracaetano.data.rest.DogDataSourceFactory
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(getListDogsInteractor: GetListDogsInteractor) : ViewModel() {

    val result : LiveData<PagedList<Dog>>
    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 10

    private val sourceFactory: DogDataSourceFactory

    init {
        sourceFactory = DogDataSourceFactory(compositeDisposable, getListDogsInteractor)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(false)
                .build()
        result = LivePagedListBuilder<Int, Dog>(sourceFactory, config).build()
    }

    fun retry() {
        sourceFactory.sourceLiveData.value!!.retry()
    }

    fun refresh() {
        sourceFactory.sourceLiveData.value!!.invalidate()
    }

    val networkState = switchMap( sourceFactory.sourceLiveData, { it.networkState })!!
    val initialLoad = switchMap( sourceFactory.sourceLiveData, { it.initialLoad })!!


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
