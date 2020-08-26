package com.example.warrenchallenge

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.abs


class ObjectivesListActivity : AppCompatActivity(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private val adapter: ObjectivesAdapter by lazy {
        ObjectivesAdapter(
            context = this@ObjectivesListActivity,
            items = listOf(
                Model("asdasd", "Disney!", 123.0, 123.0, "20/12/200"),
                Model("asdasd", "Faculdade...", 123.0, 123.0, "20/12/200"),
                Model("asdasd", "Sei la maluco", 123.0, 123.0, "20/12/200"),
                Model("asdasd", "asfsdf", 123.0, 123.0, "20/12/200"),
                Model("asdasd", "asfsdf", 123.0, 123.0, "20/12/200"),
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //app_bar.addOnOffsetChangedListener(this)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet)
        setupBottomSheet()

        rv_objectives.adapter = adapter

    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val scrollRange: Float = appBarLayout?.totalScrollRange?.toFloat() ?: 1f
        ll_visible_content.alpha = 1 - (abs(verticalOffset) / scrollRange)
    }

    private fun setupBottomSheet() {


        val sheetLayoutParams = bottomsheet.layoutParams
        val displayMetrics = DisplayMetrics()

        this.display?.getRealMetrics(displayMetrics)
        sheetLayoutParams.height = displayMetrics.heightPixels

        bottomsheet.layoutParams = sheetLayoutParams

        setupBottomSheetCallback()

    }

    private fun setupBottomSheetCallback() {

        val llPeekLayoutParams = constraintLayout.layoutParams
        val height = llPeekLayoutParams.height

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {

                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                llPeekLayoutParams.height = abs((1 - slideOffset) * height).toInt()
                constraintLayout.layoutParams = llPeekLayoutParams
            }
        })

    }
}
