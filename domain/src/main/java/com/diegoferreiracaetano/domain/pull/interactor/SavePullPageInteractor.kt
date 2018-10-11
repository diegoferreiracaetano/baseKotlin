package com.diegoferreiracaetano.domain.pull.interactor

import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Completable

class SavePullPageInteractor(private val repository: PullRepository): InteractorCompletable<SavePullPageInteractor.Request>() {

    override fun create(request: Request): Completable {
        return repository.getTotal()
                .map {it.div(request.limit).plus(1)}
                .toFlowable()
                .flatMap{repository.getList(request.owner,request.repo,it)}
                .flatMapCompletable { repository.save(it)}
    }


    data class Request(val owner: String,val repo:String,val limit:Int) : InteractorCompletable.Request()
}