package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Completable

class SaveRepoInicialInteractor(private val repository: RepoRepository): InteractorCompletable<SaveRepoInicialInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getList(request.page)
                .flatMapCompletable { repository.save(it) }
    }


    data class Request(val page:Int) : InteractorCompletable.Request()
}