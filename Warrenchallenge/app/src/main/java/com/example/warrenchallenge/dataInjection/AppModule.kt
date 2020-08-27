package com.example.warrenchallenge.dataInjection

import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.scenes.login.LoginViewModel
import com.example.warrenchallenge.scenes.objectives.ObjectivesViewModel
import com.example.warrenchallenge.service.login.ILoginAPI
import com.example.warrenchallenge.service.login.LoginService
import com.example.warrenchallenge.service.login.LoginServiceDelegate
import com.example.warrenchallenge.service.objectives.IObjectivesAPI
import com.example.warrenchallenge.service.objectives.ObjectivesService
import com.example.warrenchallenge.service.objectives.ObjectivesServiceDelegate
import com.example.warrenchallenge.util.MyDispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val dispatchersModule = module { single { MyDispatcher() } }

val serviceModule = module {
    loginServiceModule()

    objectivesServiceModule()
}

val preferencesModule = module {
    single { PreferencesManager(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { ObjectivesViewModel(get(), get(), get()) }
}

val appModule = listOf(dispatchersModule, serviceModule, viewModelModule, preferencesModule)

private fun Module.loginServiceModule() {
    single { ILoginAPI.api }

    single<LoginServiceDelegate> { LoginService(get()) }
}

private fun Module.objectivesServiceModule() {
    single { IObjectivesAPI.api }

    single<ObjectivesServiceDelegate> { ObjectivesService(get()) }
}