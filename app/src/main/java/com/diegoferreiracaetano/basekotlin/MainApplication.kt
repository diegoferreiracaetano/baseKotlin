package com.diegoferreiracaetano.basekotlin

import android.app.Application
import android.graphics.Bitmap
import com.diegoferreiracaetano.basekotlin.di.appModule
import com.diegoferreiracaetano.basekotlin.di.dbModule
import com.diegoferreiracaetano.basekotlin.di.repositoryModule
import com.diegoferreiracaetano.basekotlin.di.viewModelModule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin


class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, repositoryModule, viewModelModule, dbModule))
        val client = OkHttpClient.Builder().build()
        val config = OkHttpImagePipelineConfigFactory
                .newBuilder(this, client)
                .setBitmapsConfig(Bitmap.Config.RGB_565)
                .setDownsampleEnabled(true)
                .build()
        Fresco.initialize(this, config)
    }
}