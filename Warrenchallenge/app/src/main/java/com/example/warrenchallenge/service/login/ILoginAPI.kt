package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.model.LoginResponse
import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.service.wrapper.ApiEmptyResult
import com.example.warrenchallenge.service.wrapper.ApiResult
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface ILoginAPI {

    @POST("account/login")
    fun postLogin(
        @Body login: UserLogin
    ): Deferred<ApiResult<LoginResponse>>
}