package com.diegoferreiracaetano.domain.pull.interactor

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.InteractorCompletable
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.repo.Repo

class GetListPullInteractor(private val repository: PullRepository){

    fun execute(request: Request): DataSource.Factory<Int, Pull> {
        return repository.getList(request.owner,request.repo)
    }

    class Request(val owner: String,val repo:String)
}