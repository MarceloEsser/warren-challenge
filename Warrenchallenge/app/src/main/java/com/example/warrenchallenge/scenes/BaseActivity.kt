package com.example.warrenchallenge.scenes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.R
import com.example.warrenchallenge.scenes.dialog.ErrorDialog
import com.example.warrenchallenge.scenes.dialog.LoaderDialog

open class BaseActivity : AppCompatActivity() {

    private lateinit var loader: LoaderDialog
    private lateinit var errorDialog: ErrorDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objectives)

        loader = LoaderDialog()
    }

    fun showLoader() {
        loader.show(supportFragmentManager, "loaderDialog")
    }

    fun hideLoader() {
        loader.dismiss()
    }

    fun showErrorDialog(
        title: String? = "Oops!",
        message: String? = null,
        action: () -> Unit = {}
    ) {
        errorDialog = ErrorDialog(message, title, action)
        errorDialog.show(supportFragmentManager, "errorDialog")
    }
}