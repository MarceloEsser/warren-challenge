package com.example.warrenchallenge.dataInjection

import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.scenes.login.LoginViewModel
import com.example.warrenchallenge.service.login.ILoginAPI
import com.example.warrenchallenge.service.login.LoginService
import com.example.warrenchallenge.service.login.LoginServiceDelegate
import com.example.warrenchallenge.util.MyDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dispatchersModule = module { single { MyDispatcher() } }

val serviceModule = module {
    single { ILoginAPI.api }

    single<LoginServiceDelegate> { LoginService(get()) }
}

val preferencesModule = module {
    single { PreferencesManager(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
}

val appModule = listOf(dispatchersModule, serviceModule, viewModelModule, preferencesModule)