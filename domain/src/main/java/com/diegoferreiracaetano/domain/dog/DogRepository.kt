package com.diegoferreiracaetano.domain.dog

import io.reactivex.Flowable

interface DogRepository{
    
    fun getPhotos(breeds: List<String>): Flowable<List<Dog>>
}

