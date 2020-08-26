package com.example.warrenchallenge.scenes.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warrenchallenge.model.LoginResponse
import com.example.warrenchallenge.model.UserLogin
import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.service.login.LoginServiceDelegate
import com.example.warrenchallenge.util.MyDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel(
    private val service: LoginServiceDelegate,
    private val dispatcher: MyDispatcher,
    private val preferences: PreferencesManager
) : ViewModel() {

    val isUserLoged: Boolean = preferences.accessToken != null

    val loginResponse = MutableLiveData<LoginResponse>()

    fun doLogin(email: String, password: String) {
        val userLogin = UserLogin(email, password)

        viewModelScope.launch(dispatcher.IO) {
            service.doLogin(userLogin).collect {

                preferences.accessToken = it.data?.accessToken

                if (it.data != null) {
                    loginResponse.postValue(it.data)
                }
            }
        }
    }
}