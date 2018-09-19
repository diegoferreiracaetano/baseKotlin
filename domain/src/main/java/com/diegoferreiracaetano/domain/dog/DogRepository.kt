package com.diegoferreiracaetano.domain.dog

import io.reactivex.Flowable

interface DogRepository{
    
    fun getPhotos(): Flowable<List<Dog>>
}

