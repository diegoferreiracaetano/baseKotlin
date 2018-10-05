package com.diegoferreiracaetano.basekotlin.biding

import android.net.Uri
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.diegoferreiracaetano.basekotlin.ui.main.adapter.DogAdapter
import com.diegoferreiracaetano.data.NetworkState
import com.diegoferreiracaetano.domain.dog.Dog
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.diegoferreiracaetano.basekotlin.R



object BindingAdapters {

    @JvmStatic
    @BindingAdapter("setAdapter","retryCallback","setNetworkState", requireAll= false)
    fun RecyclerView.setReviewAdapter(items: PagedList<Dog>?,retryCallback: () -> Unit, networkState: NetworkState?) {
        items?.let {
            if(adapter == null)
                 adapter = DogAdapter(retryCallback)

            (adapter as DogAdapter).submitList(items)
            (adapter as DogAdapter).setNetworkState(networkState)
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(boolean: Boolean) {
        visibility = if(boolean) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("setLoad")
    fun SwipeRefreshLayout.setLoad(networkState: NetworkState?) {
        if(networkState?.status == NetworkState.LOADING.status){
            isRefreshing = true
        }else{
            isRefreshing = false
        }
    }

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun SimpleDraweeView.setImageUrl(url: String) {
        val request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .build()

        val newController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .build()
        this.setBackgroundColor(ContextCompat.getColor(context,R.color.primary))
        controller = newController
    }

    @JvmStatic
    @BindingAdapter("showLongMessage", "callback", requireAll = false)
    fun showLongMessage(view: View, throwable: Throwable?, callback: BaseTransientBottomBar.BaseCallback<Snackbar>? = null) {
        throwable?.let {
            val snackbar = Snackbar.make(view, "Error load", Snackbar.LENGTH_LONG)
            if (callback != null) snackbar.addCallback(callback)
            snackbar.show()
        }
    }
}