package com.diegoferreiracaetano.data.local.dog

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegoferreiracaetano.domain.dog.Dog
import io.reactivex.Single

@Dao
interface DogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entityLocals: List<DogEntityLocal>): List<Long>

    @Query("SELECT * FROM dog ORDER BY id ASC")
    fun getAll(): DataSource.Factory<Int, Dog>

    @Query("SELECT COUNT() FROM dog")
    fun getTotal() : Single<Int>
}
