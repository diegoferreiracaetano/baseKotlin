package com.diegoferreiracaetano.data.rest

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
