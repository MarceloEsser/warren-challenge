package com.example.warrenchallenge.scenes.objectives

import android.util.DisplayMetrics
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.example.warrenchallenge.R
import com.example.warrenchallenge.adapter.ObjectivesAdapter
import com.example.warrenchallenge.extensions.brazillianCurrency
import com.example.warrenchallenge.model.objective.Objective
import com.example.warrenchallenge.scenes.BaseActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_objectives.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class ObjectivesListActivity : BaseActivity(R.layout.activity_objectives) {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var adapter: ObjectivesAdapter
    private val activitContext = this@ObjectivesListActivity

    private val viewModel: ObjectivesViewModel by viewModel()

    override fun onInitValues() {
        showLoader()

        successConfiguration()
        errorConfiguration()

        img_btn_close.setOnClickListener {
            finish()
        }
    }

    private fun successConfiguration() {
        val objectivesObserver = Observer<List<Objective>> {
            screenSetup()
        }

        viewModel.objectives.observe(activitContext, objectivesObserver)
    }

    private fun screenSetup() {
        tv_amount.text = viewModel.totaIncome.brazillianCurrency()

        if (viewModel.hasObjectives) {
            configAdapter(viewModel.objectives.value ?: listOf())
            configBottomSheet()
        } else {
            tv_without_items.visibility = VISIBLE
            cl_bottom_sheet.visibility = GONE
        }

        hideLoader()
    }

    private fun errorConfiguration() {
        val errorMessageObserver = Observer<String> {
            screenSetup()
            showErrorDialog(message = it)
        }

        viewModel.errorMessage.observe(this, errorMessageObserver)
    }

    private fun configAdapter(objectives: List<Objective>) {
        adapter = ObjectivesAdapter(objectives, activitContext)
        rv_objectives.adapter = adapter
    }

    private fun configBottomSheet() {

        mBottomSheetBehavior = BottomSheetBehavior.from(cl_bottom_sheet)

        val layoutParams = cl_bottom_sheet.layoutParams
        val displayMetrics = DisplayMetrics()

        this.display?.getRealMetrics(displayMetrics)
        layoutParams.height = displayMetrics.heightPixels

        cl_bottom_sheet.layoutParams = layoutParams

        bottomSheetCallBack()
    }

    private fun bottomSheetCallBack() {

        val layoutParams = cl_header_content.layoutParams
        val height = layoutParams.height

        mBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {}

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val intOffSet = abs((1 - slideOffset) * (height + 2)).toInt()
                updateScreenFrom(layoutParams, intOffSet, slideOffset)
            }
        })

    }

    private fun updateScreenFrom(
        layoutParams: ViewGroup.LayoutParams,
        intOffSet: Int,
        slideOffset: Float
    ) {
        layoutParams.height = intOffSet
        img_btn_close.alpha = (1 - (slideOffset))
        tv_title.alpha = (1 - (slideOffset))
        ll_income_content.alpha = (1 - (slideOffset))
        cl_header_content.layoutParams = layoutParams
    }
}
