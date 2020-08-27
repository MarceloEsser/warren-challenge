package com.example.warrenchallenge.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objectives)

//        tv_token.setText(PreferencesManager.accessToken ?: "n tem token")
    }
}