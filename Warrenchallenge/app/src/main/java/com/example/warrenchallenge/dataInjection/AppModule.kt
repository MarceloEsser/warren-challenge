package com.example.warrenchallenge.dataInjection

import com.example.warrenchallenge.BuildConfig
import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.scenes.login.LoginViewModel
import com.example.warrenchallenge.scenes.objectives.ObjectivesViewModel
import com.example.warrenchallenge.service.NetworkHandler
import com.example.warrenchallenge.service.callAdapter.CallAdapterFactory
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
import org.koin.java.KoinJavaComponent.get
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dispatchersModule = module { single { MyDispatcher() } }

val serviceModule = module {

    single<LoginServiceDelegate> { LoginService(get()) }
    single<ObjectivesServiceDelegate> { ObjectivesService(get()) }
}

val networkModule = module {

    single { retrofit() }

    single { get<Retrofit>().create(IObjectivesAPI::class.java) }
    single { get<Retrofit>().create(ILoginAPI::class.java) }
}

private fun retrofit() = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create(NetworkHandler.gsonBuilder()))
    .baseUrl(BuildConfig.base_url)
    .addCallAdapterFactory(CallAdapterFactory())
    .client(NetworkHandler.httpClient())
    .build()

val preferencesModule = module {
    single { PreferencesManager(get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get(), get()) }
    viewModel { ObjectivesViewModel(get(), get(), get()) }
}

val appModule =
    listOf(networkModule, dispatchersModule, serviceModule, viewModelModule, preferencesModule)
