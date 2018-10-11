package com.diegoferreiracaetano.github.ui.pull.adapter

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.diegoferreiracaetano.domain.NetworkState
import com.diegoferreiracaetano.domain.Status
import com.diegoferreiracaetano.domain.pull.Pull

object ViewBindingAdapters{
    @JvmStatic
    @BindingAdapter("pullAdapter","pullRetry","pullCallback","pullNetworkEvents", requireAll= false)
    fun RecyclerView.setReviewAdapter(items: PagedList<Pull>?, retry: () -> Unit,
                                      callback: PullViewHolder.OnItemClickListener,
                                      networkState: NetworkState?) {
        items?.let {
            if(adapter == null)
                adapter = PullAdapter(retry,callback)
            (adapter as PullAdapter).submitList(items)

            if(items.isEmpty() && networkState?.status == Status.SUCCESS ) {
                (adapter as PullAdapter).setNetworkState(null)
            }else {
                (adapter as PullAdapter).setNetworkState(networkState)
            }
        }
    }
}