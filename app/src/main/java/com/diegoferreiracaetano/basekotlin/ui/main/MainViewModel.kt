package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.basekotlin.util.SingleLiveData
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(val repository : DogRepository) : ViewModel() {

    private val _result = SingleLiveData<List<Dog>>()
    val result = _result

    private val _error = SingleLiveData<Throwable>()
    val error = _error

    fun getDog() {
        repository.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                onNext = { result.value = it },
                onError =  { error.value = it },
                onComplete = { })

    }
}
