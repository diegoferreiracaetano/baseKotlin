package com.diegoferreiracaetano.github.di

import com.diegoferreiracaetano.data.pull.PullImpRepository
import com.diegoferreiracaetano.data.repo.RepoImpRepository
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.repo.RepoRepository
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.GetListRepoInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveCacheInteractor
import com.diegoferreiracaetano.domain.repo.interactor.SaveInicialCacheInteractor
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val repositoryModule : Module = module {

    single{ RepoImpRepository(get(),get()) as RepoRepository}
    single{ PullImpRepository(get(),get()) as PullRepository}
    single { GetListRepoInteractor(get()) }
    single { CallbackRepoInteractor(get(),get()) }
    single { SaveInicialCacheInteractor(get())}
    single { SaveCacheInteractor(get()) }

}
