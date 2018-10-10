package com.diegoferreiracaetano.domain.pull

import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PullRepository{
    fun getList(ownerName: String,repoName: String): Flowable<List<Pull>>

    fun getList(): DataSource.Factory<Int, Pull>

    fun getTotal(): Single<Int>

    fun save(list: List<Pull>): Completable

}