package com.diegoferreiracaetano.basekotlin.di

import com.diegoferreiracaetano.basekotlin.ui.main.MainViewModel
import com.diegoferreiracaetano.data.rest.DogRepositoryRemote
import com.diegoferreiracaetano.domain.dog.DogRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


private val BASE_URL = "https://dog.ceo/api/"
private val REQUEST_TIMEOUT :Long = 60

val appModule : Module = module {


    viewModel { MainViewModel(get()) }
    single { DogRepositoryRemote(get()) as DogRepository }
    single {


        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT , TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })


        httpClient.build();

    }

    single {
        Retrofit.Builder()
                .client(get ())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build() }
}
