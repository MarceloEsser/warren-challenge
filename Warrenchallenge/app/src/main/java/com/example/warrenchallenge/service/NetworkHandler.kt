package com.example.warrenchallenge.service

import com.example.warrenchallenge.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetworkHandler<T> {

    companion object {
        fun <T> getInstance(mclass: Class<T>): NetworkHandler<T> {
            return NetworkHandler<T>().getInstance(mclass)
        }
    }

    private val instance: NetworkHandler<T> by lazy {
        NetworkHandler<T>()
    }

    private val logginInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private lateinit var tClass: Class<T>

    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun build() {
        retrofitBuilder()
    }

    private fun getInstance(mclass: Class<T>): NetworkHandler<T> {
        instance.tClass = mclass

        return instance
    }

    private fun retrofitBuilder(): Retrofit {
        val gson: Gson = gsonBuilder()

        retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BuildConfig.base_url)
            .client(httpClient())
            .build()

        return retrofit
    }

    private fun gsonBuilder(): Gson {
        return GsonBuilder()
            .disableHtmlEscaping()
            .setLenient().create()
    }

    private fun httpClient(): OkHttpClient {
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logginInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return okHttpClient
    }
}