package com.diegoferreiracaetano.domain.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface PullRepository{
    fun getListRemote(owner:String,repo:String): Flowable<List<Pull>>

    fun getList(owner:String,repo:String): DataSource.Factory<Int, Pull>

    fun save(list: List<Pull>): Completable

}