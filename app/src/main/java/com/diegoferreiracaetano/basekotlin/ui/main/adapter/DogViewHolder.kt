package com.diegoferreiracaetano.basekotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.basekotlin.databinding.ItemDogBinding
import com.diegoferreiracaetano.domain.dog.Dog

class DogViewHolder(val binding: ItemDogBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(dog: Dog?) {
        binding.dog = dog
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): DogViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ItemDogBinding = DataBindingUtil.inflate(inflater, R.layout.item_dog, parent, false)
            return DogViewHolder(binding)
        }
    }
}