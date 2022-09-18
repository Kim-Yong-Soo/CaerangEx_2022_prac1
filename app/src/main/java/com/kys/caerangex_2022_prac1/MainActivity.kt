package com.kys.caerangex_2022_prac1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.kys.caerangex_2022_prac1.calendar.CalendarActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.viewpager)

        viewPager?.apply {
            clipToPadding = false
            clipChildren = false

            val compositeTransformer = CompositePageTransformer()
            compositeTransformer.addTransformer(MarginPageTransformer(40))
            compositeTransformer.addTransformer(ZoomOutPageTransformer())
            setPageTransformer(compositeTransformer)
        }

        val adapter = PageAdapter(supportFragmentManager, lifecycle)
        adapter.apply {
            addFragments(EditCommentActivity())
            addFragments(CalendarActivity())
        }

        viewPager.adapter = adapter
    }
}