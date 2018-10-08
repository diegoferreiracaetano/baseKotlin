package com.diegoferreiracaetano.basekotlin

import android.app.Application
import com.diegoferreiracaetano.basekotlin.di.appModule
import com.diegoferreiracaetano.basekotlin.di.dbModule
import com.diegoferreiracaetano.basekotlin.di.repositoryModule
import com.diegoferreiracaetano.basekotlin.di.viewModelModule
import org.koin.android.ext.android.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, repositoryModule, viewModelModule, dbModule))
    }
}