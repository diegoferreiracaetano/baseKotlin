package com.diegoferreiracaetano.domain.repo.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.repo.RepoRepository
import io.reactivex.Completable

class SaveCacheInteractor(private val repository: RepoRepository): InteractorCompletable<SaveCacheInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getTotal()
                .map {it.div(request.limit).plus(1)}
                .toFlowable()
                .flatMap{repository.getList(it)}
                .flatMapCompletable { repository.save(it)}
    }


    data class Request(val limit:Int) : InteractorCompletable.Request()
}