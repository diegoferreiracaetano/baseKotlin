package com.diegoferreiracaetano.domain.dog.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository

import io.reactivex.Flowable

class GetListDogsInteractor(private val repository: DogRepository) : InteractorFlowable<List<Dog>, GetListDogsInteractor.Request>() {

    override fun create(request: Request): Flowable<List<Dog>> {
        return repository.getList(request.limit,request.page)
    }

    data class Request(val limit:Int,val page:Int) : InteractorFlowable.Request()
}