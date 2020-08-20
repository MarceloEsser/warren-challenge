package com.example.warrenchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        lottieControl()

        img_btn_close.setOnClickListener {
            finish()
        }
    }

    private fun lottieControl() {
        lav_money_animation.speed = 1.5f
        lav_money_animation.addAnimatorUpdateListener { animator ->
            val progress = (animator.animatedValue as Float * 100).toInt()
            if (progress == 30) {
                lav_money_animation.pauseAnimation()
                ll_login_fields.visibility = VISIBLE
            }
        }
    }
}