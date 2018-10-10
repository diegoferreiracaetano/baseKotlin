package com.diegoferreiracaetano.github.ui.pull

import android.provider.SyncStateContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
                    private val callback :CallbackPullInteractor) : ViewModel() {


    private var params = MutableLiveData<Pair<String,String>>()
    val result : LiveData<PagedList<Pull>>

    init{
        result = Transformations.switchMap(params) { getList(it) }
    }

    fun getList(pair: Pair<String,String>) : LiveData<PagedList<Pull>>  {
        callback.insert(pair)
        val config = PagedList.Config.Builder()
                .setPageSize(Constants.PAGE_SIZE)
                .setInitialLoadSizeHint(Constants.PAGE_SIZE)
                .setEnablePlaceholders(true)
                .build()

        return LivePagedListBuilder<Int, Pull>(getPullInteractor.execute(GetListPullInteractor.Request(pair.first,pair.second)), config)
                .setBoundaryCallback(callback)
                .build()
    }

    fun setParams(pair: Pair<String,String>){
        this.params.value = pair
    }

    fun retry() {
        callback.retry()
    }


    fun refresh() {
        callback.onZeroItemsLoaded()
    }

    val networkState = Transformations.switchMap(result,{callback.networkState})
    val initialLoad =  Transformations.switchMap(result,{callback.initialLoad})

    override fun onCleared() {
        super.onCleared()
        callback.clear()
    }


}
