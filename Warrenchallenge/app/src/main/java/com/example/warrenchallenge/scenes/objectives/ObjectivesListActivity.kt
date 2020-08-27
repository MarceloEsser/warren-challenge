package com.example.warrenchallenge.scenes.objectives

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.warrenchallenge.model.objective.Objective
import com.example.warrenchallenge.adapter.ObjectivesAdapter
import com.example.warrenchallenge.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_objectives.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs


class ObjectivesListActivity : AppCompatActivity() {

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val viewModel: ObjectivesViewModel by viewModel()

    private lateinit var adapter: ObjectivesAdapter
    private val activitContext = this@ObjectivesListActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objectives)

        configBottomSheet()

        viewModel.loadObjectives()

        objectivesSetup()

    }

    private fun objectivesSetup() {
        val objectivesObserver = Observer<List<Objective>> {
            adapter = ObjectivesAdapter(it, activitContext)
            rv_objectives.adapter = adapter
        }

        viewModel.objectivesList.observe(activitContext, objectivesObserver)
    }

    private fun configBottomSheet() {

        mBottomSheetBehavior = BottomSheetBehavior.from(ll_bottom_sheet)

        val layoutParams = ll_bottom_sheet.layoutParams
        val displayMetrics = DisplayMetrics()

        this.display?.getRealMetrics(displayMetrics)
        layoutParams.height = displayMetrics.heightPixels

        ll_bottom_sheet.layoutParams = layoutParams

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
        tv_title.alpha = (1 - (slideOffset))
        ll_income_content.alpha = (1 - (slideOffset))
        cl_header_content.layoutParams = layoutParams
    }
}
