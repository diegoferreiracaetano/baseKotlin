package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import retrofit2.Retrofit

class DogReposistoryRemote(retrofit: Retrofit) : DogRepository {

    var api = retrofit.create(DogApi::class.java)

    override fun getPhotos(breeds: List<String>): Flowable<List<Dog>> {

      return  Flowable.just(breeds)
                .flatMap{ Flowable.fromIterable(it) }
                .take(20)
                .flatMap { string-> api.getPhoto(string).map { DogPhotoEntityRemote.parse(it) }
                .flatMapMaybe {photo-> Maybe.just(Dog(string,photo))}}
                .toList()
                .toFlowable()


    }
}
