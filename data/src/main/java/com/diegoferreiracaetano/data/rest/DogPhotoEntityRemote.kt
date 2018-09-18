package com.diegoferreiracaetano.data.rest

class DogPhotoEntityRemote {

    private lateinit var status:String
    private lateinit var message: String

    companion object {

        fun parse(dogPhotoEntityRemote: DogPhotoEntityRemote): String {

            var photo = ""

            if(dogPhotoEntityRemote.status == "success"){
                photo = dogPhotoEntityRemote.message
            }

            return photo
        }
    }
}
