package com.example.warrenchallenge.service.login

import com.example.warrenchallenge.service.NetworkHandler

class LoginService {
    private val api = NetworkHandler.getInstance(ILoginAPI::class.java).build()
}