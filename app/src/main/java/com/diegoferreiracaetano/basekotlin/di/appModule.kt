package com.diegoferreiracaetano.basekotlin.di

import com.diegoferreiracaetano.basekotlin.ui.main.MainViewModel
import com.diegoferreiracaetano.data.rest.DogRepositoryRemote
import com.diegoferreiracaetano.domain.dog.DogRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule : Module = module {
    viewModel { MainViewModel(get()) } // get() will resolve Repository instance
    single { DogRepositoryRemote() as DogRepository }
}