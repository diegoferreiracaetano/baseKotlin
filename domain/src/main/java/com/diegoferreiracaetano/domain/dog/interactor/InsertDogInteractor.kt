package com.diegoferreiracaetano.domain.dog.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogLocalRepository
import io.reactivex.Completable

class InsertDogInteractor(private val repository: DogLocalRepository) : InteractorCompletable<InsertDogInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.save(request.dogs)
    }

    data class Request(val dogs:List<Dog>) : InteractorCompletable.Request()
}

