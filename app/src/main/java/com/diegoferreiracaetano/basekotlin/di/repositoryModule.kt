package com.diegoferreiracaetano.basekotlin.di

import com.diegoferreiracaetano.data.local.dog.DogLocalRepository
import com.diegoferreiracaetano.data.remoto.rest.dog.DogRemoteRepository
import com.diegoferreiracaetano.domain.dog.DogRepository
import com.diegoferreiracaetano.domain.dog.interactor.GetTotalInteractor
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsInteractor
import com.diegoferreiracaetano.domain.dog.interactor.GetListDogsLocalInteractor
import com.diegoferreiracaetano.domain.dog.interactor.InsertDogInteractor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val repositoryModule : Module = module {

    single { DogRemoteRepository(get()) as DogRepository }
    single { DogLocalRepository(get())  as com.diegoferreiracaetano.domain.dog.DogLocalRepository}
    single { GetListDogsInteractor(get()) }
    single { GetListDogsLocalInteractor(get()) }
    single { InsertDogInteractor(get()) }
    single { GetTotalInteractor(get()) }
}
