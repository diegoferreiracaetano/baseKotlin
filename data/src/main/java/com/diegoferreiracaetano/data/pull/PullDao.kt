package com.diegoferreiracaetano.data.pull

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.diegoferreiracaetano.domain.pull.Pull
import io.reactivex.Single

@Dao
interface PullDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(entityLocals: List<Pull>): List<Long>

    @Query("SELECT * FROM pull " +
            "ORDER BY pull.id ASC")
    fun getAll(): DataSource.Factory<Int, Pull>

    @Query("SELECT COUNT() FROM pull")
    fun getTotal() : Single<Int>
}
