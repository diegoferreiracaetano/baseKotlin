package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.Flowable
import retrofit2.Retrofit

class DogRepositoryRemote(retrofit: Retrofit) : DogRepository {

    var api = retrofit.create(DogApi::class.java)

    override fun getList(): Flowable<List<Dog>> {
        return api.getList().map { bog -> DogEntityRemote.parse(bog) }
    }
}
