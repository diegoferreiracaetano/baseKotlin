package com.diegoferreiracaetano.data.rest

import io.reactivex.Flowable
import retrofit2.http.GET

interface DogApi{

    @GET("breeds/list/all")
    fun getList(): Flowable<DogEntityRemote>
}
