package com.diegoferreiracaetano.domain.repo

import androidx.paging.DataSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface RepoRepository{
    fun getList(page:Int): Flowable<List<Repo>>

    fun getList(): DataSource.Factory<Int, Repo>

    fun getTotal(): Single<Int>

    fun save(list: List<Repo>): Completable

}