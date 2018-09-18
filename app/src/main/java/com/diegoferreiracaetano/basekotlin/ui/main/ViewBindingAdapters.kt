package com.diegoferreiracaetano.basekotlin.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.diegoferreiracaetano.domain.dog.Dog


object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("setReviewAdapter")
    fun setReviewAdapter(recyclerView: RecyclerView, items: List<Dog>?) {
        items?.let {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = AnimalListAdapter(it)
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.setImageUrl(url: String) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .into(this)
    }
}