package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog

data class DogEntityRemote(var id:Int,var name:String) {

    companion object {

        fun parse(dogEntityRemotes: List<DogEntityRemote>): List<Dog> {

            val list = ArrayList<Dog>();

            dogEntityRemotes.forEach{
                list.add(Dog(it.id,it.name,""))
            }

            return list
        }
    }
}
