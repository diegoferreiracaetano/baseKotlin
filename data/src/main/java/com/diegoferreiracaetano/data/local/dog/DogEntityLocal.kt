package com.diegoferreiracaetano.data.local.dog

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.diegoferreiracaetano.domain.dog.Dog


@Entity(tableName = "dog")
data class DogEntityLocal(@PrimaryKey var id:Int,var breed: String, var photo: String){
    constructor():this(0,"","")

    companion object {

        @Ignore
        fun convert(dogListLocal:List<Dog>):List<DogEntityLocal>{
            val list = mutableListOf<DogEntityLocal>()
            for(dog in dogListLocal){
                val dogLocal = DogEntityLocal(dog.id,dog.breed,dog.photo)
                list.add(dogLocal)
            }
            return list.toList()
        }

        @Ignore
        fun parse(dogListLocal:List<DogEntityLocal>):List<Dog>{
            val list = mutableListOf<Dog>()
            for(dogLocal in dogListLocal){
                val dog = Dog(dogLocal.id,dogLocal.breed,dogLocal.photo)
                list.add(dog)
            }
            return list.toList()
        }
    }

}

