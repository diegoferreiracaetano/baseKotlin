package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.basekotlin.util.SingleLiveData
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class MainViewModel(val repository : DogRepository) : ViewModel() {

    val result = MutableLiveData<List<Dog>>()

    private val _error = SingleLiveData<Throwable>()
    val error = _error

    fun getPhotoDog(breeds:List<String>) {
        repository.getPhotos(breeds)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                onNext = { result.postValue(it) },
                onError =  { error.value = it },
                onComplete = { })

    }
}
