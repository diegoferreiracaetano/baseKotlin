package com.diegoferreiracaetano.domain.dog

import io.reactivex.Flowable


interface DogRepository{
    
    fun getList(limit: Int,page: Int): Flowable<List<Dog>>
}

