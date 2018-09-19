package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import retrofit2.Retrofit

class DogReposistoryRemote(retrofit: Retrofit) : DogRepository {

    var api = retrofit.create(DogApi::class.java)

    override fun getPhotos(): Flowable<List<Dog>> {

        return  api.getList(10,0)
                .map { DogEntityRemote.parse(it) }
                .flatMap{ Flowable.fromIterable(it) }
                .flatMap { dog-> api.getPhoto(dog.id).map { DogPhotoEntityRemote.parse(it) }
                        .flatMapMaybe {photo-> Maybe.just(dog.copy(dog.id,dog.breed,photo))}}
                .toList()
                .toFlowable()

    }
}
