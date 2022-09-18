package com.kys.caerangex_2022_prac1

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var dataSet = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun createFragment(position: Int): Fragment {
        return dataSet[position]
    }

    fun addFragments(fragment: Fragment) {
        dataSet.add(fragment)
        notifyItemInserted(dataSet.size - 1)
    }

}