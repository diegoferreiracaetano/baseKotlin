package com.diegoferreiracaetano.data.remoto.rest.dog

data class DogPhotoEntityRemote(var id:String,var url:String) {

    companion object {

        fun parse(dogPhotoEntityRemotes: List<DogPhotoEntityRemote>): String {
            dogPhotoEntityRemotes.forEach{
                return it.url
            }
            return ""
        }
    }
}
