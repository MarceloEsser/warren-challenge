package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.wrapper.resource.NetworkBoundResource
import com.example.warrenchallenge.service.wrapper.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LoginServiceDelegate {
    suspend fun doLogin(userLogin: UserLogin): Flow<Resource<Any?>>
}

class LoginService() : LoginServiceDelegate {
    private val mApi: ILoginAPI = NetworkHandler.getInstance(ILoginAPI::class.java).build()

    override suspend fun doLogin(userLogin: UserLogin): Flow<Resource<Any?>> {
        return flow {
            NetworkBoundResource(
                collector = this,
                processResponse = { it },
                call = mApi.postLogin(userLogin)
            ).build()
        }
    }
}