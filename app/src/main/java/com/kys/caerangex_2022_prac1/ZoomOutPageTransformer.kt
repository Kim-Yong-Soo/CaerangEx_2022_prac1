package com.kys.caerangex_2022_prac1

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min

private const val MIN_SCALE = 0.95f
private const val YM = 40.0
private const val Y0 = 0.0
private const val K = 10.0

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < -1 -> {
                    alpha = 0f
                }
                position <= 1 -> {
                    val scaleFactor = max(MIN_SCALE, 1 - abs(position))

                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> {
                    alpha = 0f
                }
            }
        }
    }
}