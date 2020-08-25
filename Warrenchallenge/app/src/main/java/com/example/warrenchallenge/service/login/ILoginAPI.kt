package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.BuildConfig
import com.example.warrenchallenge.model.LoginResponse
import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.adapter.CallAdapterFactory
import com.example.warrenchallenge.service.wrapper.ApiEmptyResult
import com.example.warrenchallenge.service.wrapper.ApiResult
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginAPI {

    companion object {

        val api: ILoginAPI
            get() = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(NetworkHandler.gsonBuilder()))
                .baseUrl(BuildConfig.base_url)
                .addCallAdapterFactory(CallAdapterFactory())
                .client(NetworkHandler.httpClient())
                .build()
                .create(ILoginAPI::class.java)

    }

    @POST("account/login")
    fun postLogin(
        @Body login: UserLogin
    ): Deferred<ApiResult<LoginResponse>>
}