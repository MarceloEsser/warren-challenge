package com.example.warrenchallenge.scenes.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment


abstract class BaseDialog(
    private val layout: Int,
    private val height: Int? = null,
    private val width: Int? = null,
    private val gravity: Int = Gravity.CENTER,
    private val backgroundColor: Int = Color.TRANSPARENT
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
        onCreateDialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return onCreateDialog
    }


    private fun setupDialog(dialog: Dialog?) {
        dialog?.run {
            if (height != null)
                window?.attributes?.height = height
            if (width != null)
                window?.attributes?.width = width
            window?.setGravity(gravity)
            window?.setBackgroundDrawable(ColorDrawable(backgroundColor));
        }
    }

    override fun onStart() {
        super.onStart()
        setupDialog(dialog)
    }

}