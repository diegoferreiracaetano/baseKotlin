package com.diegoferreiracaetano.domain.dog.interactor

import com.diegoferreiracaetano.domain.dog.DogLocalRepository

class GetListDogsLocalInteractor(private val repository: DogLocalRepository) {

    var dataSource = repository.getList()

}
