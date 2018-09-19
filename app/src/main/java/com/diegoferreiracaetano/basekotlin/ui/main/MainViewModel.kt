package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(val getListDogsInteractor: GetListDogsInteractor) : ViewModel() {

    val result = MutableLiveData<List<Dog>>()
    val error = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    fun getList() : Disposable {

        loading.postValue(true)

        return getListDogsInteractor.execute(GetListDogsInteractor.Request())
                .subscribeBy (
                        onNext = {
                            result.postValue(it)
                            loading.postValue(false)
                            empty.postValue(it.isEmpty())
                        },
                        onError =  {
                            loading.postValue(false)
                            error.postValue(it) },
                        onComplete = { })

    }
}
