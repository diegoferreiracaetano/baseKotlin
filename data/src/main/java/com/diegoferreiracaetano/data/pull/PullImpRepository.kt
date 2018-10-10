package com.diegoferreiracaetano.data.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.api.GithubApi
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import com.diegoferreiracaetano.domain.repo.Repo
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import retrofit2.Retrofit

class PullImpRepository(private var dao: PullDao,
                        private val retrofit: Retrofit) : PullRepository {

    private val api = retrofit.create(GithubApi::class.java)

    override fun getListRemote(owner:String,repo:String): Flowable<List<Pull>> {
        return api.getPull(owner,repo)
                .flatMap{Flowable.fromIterable(it)}
                .flatMapMaybe{Maybe.just(it.copy(ownerName = owner,repoName = repo))}
                .toList()
                .toFlowable()
    }

    override fun getList(owner:String,repo:String): DataSource.Factory<Int, Pull> {
        return dao.getAll(owner,repo)
    }

    override fun save(list: List<Pull>): Completable {
        dao.save(list)
        return Completable.complete()
    }

}
