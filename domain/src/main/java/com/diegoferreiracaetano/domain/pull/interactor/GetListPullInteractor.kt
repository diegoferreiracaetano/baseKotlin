package com.diegoferreiracaetano.domain.pull.interactor

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository

class GetListPullInteractor(private val repository: PullRepository){

    fun execute(): DataSource.Factory<Int, Pull> {
        return repository.getList()
    }

}