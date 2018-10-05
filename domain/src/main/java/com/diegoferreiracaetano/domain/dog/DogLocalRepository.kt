package com.diegoferreiracaetano.domain.dog

import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.Single


interface DogLocalRepository{

    fun getList(): DataSource.Factory<Int, Dog>

    fun getTotal(): Single<Int>

    fun save(dogs: List<Dog>): Completable
}

