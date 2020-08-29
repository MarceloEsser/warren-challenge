package com.example.warrenchallenge.scenes

import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.example.warrenchallenge.scenes.dialog.ErrorDialog
import com.example.warrenchallenge.scenes.dialog.LoaderDialog

abstract class BaseActivity(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    private lateinit var loader: LoaderDialog
    private lateinit var errorDialog: ErrorDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        loader = LoaderDialog()

        onInitValues()
    }

    protected abstract fun onInitValues()

    fun showLoader() {
        loader.show(supportFragmentManager, "loaderDialog")
    }

    fun hideLoader() {
        loader.dismiss()
    }

    fun showErrorDialog(
        title: String = "Oops!",
        message: String = "Unknown error",
        action: () -> Unit = {}
    ) {
        errorDialog = ErrorDialog(message, title, action)
        errorDialog.show(supportFragmentManager, "errorDialog")
    }

}