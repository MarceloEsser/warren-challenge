package com.example.warrenchallenge.service.objectives

import com.example.warrenchallenge.BuildConfig
import com.example.warrenchallenge.model.Objective
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.callAdapter.CallAdapterFactory
import com.example.warrenchallenge.service.wrapper.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface IObjectivesAPI {
    companion object {

        val api: IObjectivesAPI
            get() = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(NetworkHandler.gsonBuilder()))
                .baseUrl(BuildConfig.base_url)
                .addCallAdapterFactory(CallAdapterFactory())
                .client(NetworkHandler.httpClient())
                .build()
                .create(IObjectivesAPI::class.java)

    }

    @GET("portfolios/mine")
    fun getObjectives(
        @Header("access_token") accessToken: String
    ): Deferred<ApiResult<List<Objective>>>
}