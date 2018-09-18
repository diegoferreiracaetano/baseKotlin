package com.diegoferreiracaetano.data.rest

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApi{

    @GET("breeds/list/all")
    fun getList(): Flowable<DogEntityRemote>

    @GET("breed/{breed}/images/random")
    fun getPhoto(@Path("breed") breed: String): Flowable<DogPhotoEntityRemote>
}
