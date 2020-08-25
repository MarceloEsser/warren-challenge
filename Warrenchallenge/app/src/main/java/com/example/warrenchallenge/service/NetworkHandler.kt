package com.example.warrenchallenge.service

import com.example.warrenchallenge.BuildConfig
import com.example.warrenchallenge.service.adapter.CallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkHandler {

    private val logginInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    private fun retrofitBuilder(): Retrofit {
        val gson: Gson = gsonBuilder()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.base_url)
            .addCallAdapterFactory(CallAdapterFactory())
            .client(httpClient())
            .build()

    }

    private fun gsonBuilder(): Gson {
        return GsonBuilder()
            .disableHtmlEscaping()
            .setLenient().create()
    }

    fun httpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

    }
}