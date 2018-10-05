package com.diegoferreiracaetano.data.local.dog

import androidx.paging.DataSource
import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogLocalRepository
import io.reactivex.Completable
import io.reactivex.Single

class DogLocalRepository(var dao: DogDao) : DogLocalRepository {

    override fun getList(): DataSource.Factory<Int, Dog> {
        return dao.getAll()
    }

    override fun save(dogs: List<Dog>): Completable {
        dao.save(DogEntityLocal.convert(dogs))
        return Completable.complete()
    }

    override fun getTotal(): Single<Int> {
        return dao.getTotal()
    }

}
