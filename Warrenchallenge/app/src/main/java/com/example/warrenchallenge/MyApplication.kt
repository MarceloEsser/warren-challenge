package com.example.warrenchallenge

import android.app.Application
import com.example.warrenchallenge.persistence.PreferencesManager

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferencesManager.initialize(this)
    }
}