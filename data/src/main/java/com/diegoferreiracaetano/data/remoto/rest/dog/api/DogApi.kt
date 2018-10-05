package com.diegoferreiracaetano.data.remoto.rest.dog.api

import com.diegoferreiracaetano.data.remoto.rest.dog.DogEntityRemote
import com.diegoferreiracaetano.data.remoto.rest.dog.DogPhotoEntityRemote
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface DogApi{

    @GET("breeds")
    fun getList(@Query("limit") limit:Int, @Query("page") page:Int): Flowable<List<DogEntityRemote>>

    @GET("images/search")
    fun getPhoto(@Query("breed_id") breed: Int): Flowable<List<DogPhotoEntityRemote>>
}
