package com.tearnsv.tearnapp.ui.search.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tearnsv.tearnapp.ui.search.SearchCourseFragment
import com.tearnsv.tearnapp.ui.search.SearchTutorFragment

class SearchAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        val TAB_COUNT = 2
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchCourseFragment()
            1 -> SearchTutorFragment()
            else -> SearchCourseFragment()
        }
    }
}