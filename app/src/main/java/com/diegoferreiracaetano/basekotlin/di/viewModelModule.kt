package com.diegoferreiracaetano.basekotlin.di

import com.diegoferreiracaetano.basekotlin.ui.repo.RepoViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val viewModelModule : Module = module {
    viewModel { RepoViewModel(get(), get()) }
}
