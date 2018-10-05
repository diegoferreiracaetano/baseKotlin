package com.diegoferreiracaetano.data.remoto.rest.dog.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import io.reactivex.disposables.CompositeDisposable

class DogDataSourceFactory (
        private val disposable: CompositeDisposable,
        private val interactor: GetListDogsInteractor) : DataSource.Factory<Int, Dog>() {

    val sourceLiveData = MutableLiveData<DogDataSource>()

    override fun create(): DataSource<Int, Dog> {
        val dataSource = DogDataSource(disposable, interactor)
        sourceLiveData.postValue(dataSource)
        return dataSource
    }

}