package com.diegoferreiracaetano.basekotlin

import android.app.Application
import com.diegoferreiracaetano.basekotlin.di.appModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(this, listOf(appModule))
    }
}