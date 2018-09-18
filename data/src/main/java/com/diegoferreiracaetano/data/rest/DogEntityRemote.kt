package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog

class DogEntityRemote {

    private var status: String? = null
    private var message: Map<String,List<String>>? = null

    companion object {

        fun parse(dogEntityRemote: DogEntityRemote): List<Dog> {

            val list = ArrayList<Dog>();

            for ((key, value) in dogEntityRemote.message!!) {
                var dog : Dog? = null
                if(value.size == 0)
                   dog = Dog(key, "")
                else{
                   value.forEach{
                    dog = Dog(key +"_"+it,"")
                   }
                }
                list.add(dog!!)
            }

            return list
        }
    }
}
