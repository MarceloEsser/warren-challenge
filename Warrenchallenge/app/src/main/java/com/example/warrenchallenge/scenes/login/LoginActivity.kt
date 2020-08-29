package com.example.warrenchallenge.scenes.login

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.animation.*
import androidx.lifecycle.Observer
import com.example.warrenchallenge.R
import com.example.warrenchallenge.model.login.LoginResponse
import com.example.warrenchallenge.scenes.BaseActivity
import com.example.warrenchallenge.scenes.objectives.ObjectivesListActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginActivity : BaseActivity(R.layout.activity_login) {

    private val viewModel: LoginViewModel by viewModel()

    override fun onInitValues() {
        img_btn_close.setOnClickListener {
            finish()
        }

        btn_login.setOnClickListener {
            showLoader()
            viewModel.doLogin(email_edit_text.text.toString(), password_edit_text.text.toString())
        }

        loginObservableControl()

        loginFieldsFadeInControl()
    }

    private fun loginObservableControl() {
        val loginObservable = Observer<LoginResponse> { _ ->
            hideLoader()
            doLogin()
        }

        viewModel.loginResponse.observe(this, loginObservable)
    }

    private fun loginFieldsFadeInControl() {
        ll_login_fields.visibility = VISIBLE
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

    private fun doLogin() {
        startActivity(Intent(this, ObjectivesListActivity::class.java))
        finish()
    }
}