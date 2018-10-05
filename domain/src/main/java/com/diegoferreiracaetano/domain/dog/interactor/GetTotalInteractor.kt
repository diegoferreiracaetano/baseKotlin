package com.diegoferreiracaetano.domain.dog.interactor

import com.diegoferreiracaetano.domain.InteractorSingle
import com.diegoferreiracaetano.domain.dog.DogLocalRepository
import io.reactivex.Single

class GetTotalInteractor(private val repository: DogLocalRepository) : InteractorSingle<Int, GetTotalInteractor.Request>() {

    override fun create(request: Request): Single<Int> {
        return repository.getTotal()
    }

    class Request() : InteractorSingle.Request()
}