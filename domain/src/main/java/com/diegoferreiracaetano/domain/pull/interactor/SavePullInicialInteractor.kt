package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Completable

class SavePullInicialInteractor(private val repository: PullRepository): InteractorCompletable<SavePullInicialInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getList(request.owner,request.repo,request.page)
                .flatMapCompletable { repository.save(it) }
    }


    data class Request(val owner: String,val repo:String,val page: Int) : InteractorCompletable.Request()
}