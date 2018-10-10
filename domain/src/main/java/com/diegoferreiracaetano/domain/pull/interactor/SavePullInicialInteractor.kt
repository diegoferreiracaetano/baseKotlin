package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Completable

class SavePullInicialInteractor(private val repository: PullRepository): InteractorCompletable<SavePullInicialInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getList(request.ownerName,request.repoName)
                .flatMapCompletable { repository.save(it) }
    }


    data class Request(val ownerName: String,val repoName: String) : InteractorCompletable.Request()
}