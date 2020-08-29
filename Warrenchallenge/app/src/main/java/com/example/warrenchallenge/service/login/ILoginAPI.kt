package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.BuildConfig
import com.example.warrenchallenge.model.login.LoginResponse
import com.example.warrenchallenge.model.login.UserLogin
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.callAdapter.CallAdapterFactory
import com.example.warrenchallenge.service.wrapper.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginAPI {

    @POST("account/login")
    fun postLogin(
        @Body login: UserLogin
    ): Deferred<ApiResult<LoginResponse>>
}