package com.diegoferreiracaetano.basekotlin.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.domain.dog.Dog
import kotlinx.android.synthetic.main.animal_list_item.view.*

class AnimalAdapter(val items : List<Dog>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.animal_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvAnimalType?.text = items.get(position).breed
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvAnimalType = view.tv_animal_type
}