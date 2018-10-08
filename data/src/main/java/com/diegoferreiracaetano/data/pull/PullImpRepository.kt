package com.diegoferreiracaetano.data.pull

import androidx.paging.DataSource
import com.diegoferreiracaetano.data.api.GithubApi
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.PullRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Retrofit

class PullImpRepository(private var dao: PullDao,
                        private val retrofit: Retrofit) : PullRepository {

    private val api = retrofit.create(GithubApi::class.java)

    override fun getList(ownerName: String, repoName: String): Flowable<List<Pull>> {
        return api.getPull(ownerName,repoName)
    }

    override fun getList(): DataSource.Factory<Int, Pull> {
        return dao.getAll()
    }

    override fun save(list: List<Pull>): Completable {
        dao.save(list)
        return Completable.complete()
    }

    override fun getTotal(): Single<Int> {
        return dao.getTotal()
    }

}
