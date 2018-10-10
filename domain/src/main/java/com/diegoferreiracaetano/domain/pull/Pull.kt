package com.diegoferreiracaetano.domain.pull

import androidx.room.*
import com.diegoferreiracaetano.domain.owner.Owner
import com.diegoferreiracaetano.domain.repo.Repo
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

@Entity(tableName = "pull")
data class Pull(@PrimaryKey
                val id:Long,
                val title:String,
                @SerializedName("created_at")
                val date:Date,
                val body:String?,
                @SerializedName("user")
                @Embedded(prefix = "user_")
                var owner: Owner,
                @ColumnInfo(name = "owner_name")
                var ownerName: String,
                @ColumnInfo(name = "repo_name")
                var repoName: String): Serializable {
    constructor():this(0,"",Date(),"", Owner(), "","")
}