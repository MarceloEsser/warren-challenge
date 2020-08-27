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


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        start()
    }

    private fun start() {
        GlobalScope.launch {
            delay(2000)

            if (PreferencesManager(this@SplashActivity).accessToken != null) {
                startActivity(Intent(this@SplashActivity, ObjectivesListActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            }
            finish()
        }

    }
}

