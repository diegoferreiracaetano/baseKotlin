package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.dog.Dog
import com.diegoferreiracaetano.domain.dog.DogRepository

class DogRepositoryRemote : DogRepository {

    override fun get(): Dog {
        return Dog("PitBull", "")
    }
}
