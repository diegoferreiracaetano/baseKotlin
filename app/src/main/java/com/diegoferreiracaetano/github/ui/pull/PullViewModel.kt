package com.diegoferreiracaetano.github.ui.pull

import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.diegoferreiracaetano.domain.Constants
import com.diegoferreiracaetano.domain.pull.Pull
import com.diegoferreiracaetano.domain.pull.interactor.CallbackPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.GetListPullInteractor
import com.diegoferreiracaetano.domain.pull.interactor.SavePullInicialInteractor
import com.diegoferreiracaetano.domain.repo.Repo
import com.diegoferreiracaetano.domain.repo.interactor.CallbackRepoInteractor

class PullViewModel(private val getPullInteractor: GetListPullInteractor,
                    private val savePullInicialInteractor: SavePullInicialInteractor) : ViewModel() {


    private val params = MutableLiveData<Pair<String,String>>()
    private var callback :CallbackPullInteractor

    private var repoResult = map(params, {
         callback = CallbackPullInteractor(it.first,it.second,savePullInicialInteractor)
         val config = PagedList.Config.Builder()
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()

         LivePagedListBuilder<Int, Pull>(getPullInteractor.execute(), config)
                .setBoundaryCallback(callback)
                .build()
    })


    val posts = switchMap(repoResult, { it.pagedList })!!
    val networkState = switchMap(repoResult, { it.networkState })!!
    val refreshState = switchMap(repoResult, { it.refreshState })!!
    fun retry() {
        callback.retry()
    }


    fun refresh() {
        callback.onZeroItemsLoaded()
    }

    val networkState = callback.networkState
    val initialLoad =  callback.initialLoad

    override fun onCleared() {
        super.onCleared()
        callback.clear()
    }


}
