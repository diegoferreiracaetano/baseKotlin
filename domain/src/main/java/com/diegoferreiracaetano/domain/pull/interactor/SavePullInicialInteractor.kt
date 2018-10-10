package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable

class SavePullInicialInteractor(private val repository: PullRepository): InteractorCompletable<SavePullInicialInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getListRemote(request.owner,request.repo)
                .flatMapCompletable { repository.save(it) }
    }


    data class Request(val owner: String,val repo:String) : InteractorCompletable.Request()
}