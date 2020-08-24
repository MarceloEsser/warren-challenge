package com.example.warrenchallenge.scenes.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.model.LoginResponse
import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.persistence.SessionManager
import com.example.warrenchallenge.service.login.LoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    val isUserLoged: Boolean = SessionManager.accessToken != null

    val loginResponse = MutableLiveData<LoginResponse>()

    private val service: LoginService = LoginService()

    fun doLogin(email: String, password: String) {
        val userLogin = UserLogin(email, password)

        viewModelScope.launch(Dispatchers.IO) {
            service.doLogin(userLogin).collect {

                SessionManager.accessToken = it.data?.accessToken

                if (it.data != null) {
                    loginResponse.postValue(it.data)
                }
            }
        }
    }
}