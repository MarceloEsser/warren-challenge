package com.example.warrenchallenge.scenes.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.warrenchallenge.R
import kotlinx.android.synthetic.main.dialog_error.*

class ErrorDialog(
    private val message: String,
    private val title: String,
    private val action: () -> Unit = {}
) : BaseDialog(layout = R.layout.dialog_error) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_title.text = title
        tv_message.text = message

        btn_action.setOnClickListener {
            action()
            dismiss()
        }
    }
}