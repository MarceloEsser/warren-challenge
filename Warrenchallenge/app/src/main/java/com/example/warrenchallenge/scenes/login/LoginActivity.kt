package com.example.warrenchallenge.scenes.login

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.animation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.warrenchallenge.scenes.objectives.ObjectivesListActivity
import com.example.warrenchallenge.R
import com.example.warrenchallenge.model.login.LoginResponse
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        img_btn_close.setOnClickListener {
            finish()
        }

        btn_login.setOnClickListener {
            viewModel.doLogin(email_edit_text.text.toString(), password_edit_text.text.toString())
        }

        loginObservableControl()

        loginFieldsFadeInControl()
    }

    private fun loginObservableControl() {
        val loginObservable = Observer<LoginResponse> { _ ->
            doLogin()
        }

        viewModel.loginResponse.observe(this, loginObservable)
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
                isToDoLoginAgain()
            }
        }
    }

    private fun isToDoLoginAgain() {
        if (!viewModel.isUserLoged)
            ll_login_fields.visibility = VISIBLE
        else
            doLogin()
    }

    private fun doLogin() {
        startActivity(Intent(this, ObjectivesListActivity::class.java))
        finish()
    }
}