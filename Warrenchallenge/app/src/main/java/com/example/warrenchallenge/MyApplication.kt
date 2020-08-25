package com.example.warrenchallenge

import android.app.Application
import com.example.warrenchallenge.dataInjection.appModule
import com.example.warrenchallenge.persistence.PreferencesManager
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
        PreferencesManager.initialize(this)
    }
}