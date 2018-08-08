package com.diegoferreiracaetano.basekotlin.ui.main

import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository

class MainViewModel(val repository : DogRepository) : ViewModel() {

    fun getDog() :Dog {
        return repository.get();
    }
}
