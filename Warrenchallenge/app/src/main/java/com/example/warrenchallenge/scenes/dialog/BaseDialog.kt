package com.example.warrenchallenge.scenes.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment


abstract class BaseDialog(
    private val layout: Int
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val onCreateDialog = super.onCreateDialog(savedInstanceState)
        setupDialog(onCreateDialog)
        return onCreateDialog
    }

    override fun onStart() {
        super.onStart()
        setupDialog(dialog)
    }

    private fun setupDialog(dialog: Dialog?) {
        dialog?.window?.apply {
            attributes?.height = ViewGroup.LayoutParams.MATCH_PARENT
            attributes?.width = ViewGroup.LayoutParams.MATCH_PARENT
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            setGravity(Gravity.CENTER)
        }
    }


}