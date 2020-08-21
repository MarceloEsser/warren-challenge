package com.example.warrenchallenge.scenes.login

import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.R
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        img_btn_close.setOnClickListener {
            finish()
        }

        loginFieldsFadeInControl()
    }

    override fun onResume() {
        super.onResume()

        lottieControl()
    }

    private fun loginFieldsFadeInControl() {
        val set = AnimationSet(true)

        var animation: Animation = AlphaAnimation(0.0f, 1.0f)
        animation.duration = 200
        set.addAnimation(animation)

        animation = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        )

        animation.setDuration(200)
        set.addAnimation(animation)

        val controller = LayoutAnimationController(set, 0.5f)
        ll_login_fields.layoutAnimation = controller
    }

    private fun lottieControl() {
        lav_money_animation.addAnimatorUpdateListener { animator ->
            val progress = (animator.animatedValue as Float * 100).toInt()
            lav_money_animation.speed = 1.5f
            if (progress > 40) {
                lav_money_animation.pauseAnimation()
                ll_login_fields.visibility = VISIBLE
            }
        }
    }
}