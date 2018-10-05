package com.diegoferreiracaetano.data.remoto.rest.dog

import com.diegoferreiracaetano.data.remoto.rest.dog.api.DogApi
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import retrofit2.Retrofit

class DogRemoteRepository(retrofit: Retrofit) : DogRepository {

    private var api = retrofit.create(DogApi::class.java)

    override fun getList(limit:Int,page:Int): Flowable<List<Dog>> {

        return  api.getList(limit,page)
                .map { DogEntityRemote.parse(it) }
                .flatMap{ Flowable.fromIterable(it) }
                .flatMap { dog-> api.getPhoto(dog.id).map { DogPhotoEntityRemote.parse(it) }
                        .flatMapMaybe {photo-> Maybe.just(dog.copy(dog.id,dog.breed,photo))}
                        .filter { it.photo.isNotEmpty() }}
                .toList()
                .toFlowable()

    }
}
