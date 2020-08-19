package com.example.warrenchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lav_money_animation.addAnimatorUpdateListener { animator ->
            val progress = (animator.animatedValue as Float * 100).toInt()
            if (progress == 80) {
                lav_money_animation.pauseAnimation()
            }
        }
    }
}