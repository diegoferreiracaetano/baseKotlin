package com.diegoferreiracaetano.basekotlin.ui.main.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.basekotlin.R
import com.diegoferreiracaetano.data.rest.NetworkState
import com.diegoferreiracaetano.domain.dog.Dog

class DogAdapter(private val retryCallback: () -> Unit) :
         PagedListAdapter<Dog, RecyclerView.ViewHolder>(DOG_COMPARATOR) {

    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when (viewType) {
            R.layout.item_dog -> DogViewHolder.create(parent)
            R.layout.item_network_state -> NetworkStatusViewHolder.create(parent,retryCallback)
            else -> throw IllegalArgumentException("unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.item_dog -> (holder as DogViewHolder).bindTo(getItem(position))
            R.layout.item_network_state ->(holder as NetworkStatusViewHolder).bindTo(networkState)
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.item_network_state
        } else {
            R.layout.item_dog

        }
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        if (currentList != null) {
            if (currentList!!.size != 0) {
                val previousState = this.networkState
                val hadExtraRow = hasExtraRow()
                this.networkState = newNetworkState
                val hasExtraRow = hasExtraRow()
                if (hadExtraRow != hasExtraRow) {
                    if (hadExtraRow) {
                        notifyItemRemoved(super.getItemCount())
                    } else {
                        notifyItemInserted(super.getItemCount())
                    }
                } else if (hasExtraRow && previousState !== newNetworkState) {
                    notifyItemChanged(itemCount - 1)
                }
            }
        }
    }

    companion object {
        val DOG_COMPARATOR = object : DiffUtil.ItemCallback<Dog>() {
            override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean =
                    oldItem.breed == newItem.breed
        }
    }
}