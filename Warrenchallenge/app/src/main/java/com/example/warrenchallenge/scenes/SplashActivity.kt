package com.example.warrenchallenge.scenes

import android.content.Intent
import android.os.Bundle
import com.example.warrenchallenge.R
import com.example.warrenchallenge.persistence.PreferencesManager
import com.example.warrenchallenge.scenes.login.LoginActivity
import com.example.warrenchallenge.scenes.objectives.ObjectivesListActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashActivity : BaseActivity(R.layout.activity_splash) {

    override fun onInitValues() {
        start()
    }

    private fun start() {
        GlobalScope.launch {
            delay(1500)

            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            if (PreferencesManager(this@SplashActivity).accessToken != null) {
//                startActivity(Intent(this@SplashActivity, ObjectivesListActivity::class.java))
//            } else {
//            }
            finish()
        }

    }
}

