package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.model.login.LoginResponse
import com.example.warrenchallenge.model.login.UserLogin
import com.example.warrenchallenge.service.wrapper.resource.NetworkBoundResource
import com.example.warrenchallenge.service.wrapper.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LoginServiceDelegate {
    suspend fun doLogin(userLogin: UserLogin): Flow<Resource<LoginResponse?>>
}

class LoginService(
    private val mApi: ILoginAPI
) : LoginServiceDelegate {

    override suspend fun doLogin(userLogin: UserLogin): Flow<Resource<LoginResponse?>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = mApi.postLogin(userLogin)
            ).build()
        }
    }
}