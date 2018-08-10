package com.diegoferreiracaetano.domain.dog

import io.reactivex.Flowable

interface DogRepository{
    fun getList(): Flowable<List<Dog>>
}

