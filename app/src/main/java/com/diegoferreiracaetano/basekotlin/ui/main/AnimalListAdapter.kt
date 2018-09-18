package com.diegoferreiracaetano.basekotlin.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.basekotlin.databinding.AnimalListItemBinding
import com.diegoferreiracaetano.domain.dog.Dog

class AnimalListAdapter(private val items: List<Dog>) :
        RecyclerView.Adapter<AnimalListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: AnimalListItemBinding = DataBindingUtil.inflate(inflater, R.layout.animal_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.dog = items[position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: AnimalListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
        }
    }
}