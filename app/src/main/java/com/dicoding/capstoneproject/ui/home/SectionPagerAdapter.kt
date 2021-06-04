@file:Suppress("DEPRECATION")

package com.dicoding.capstoneproject.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.capstoneproject.ui.bookmark.BookmarkFragment
import com.dicoding.capstoneproject.ui.filter.FilterFragment
import com.dicoding.capstoneproject.R
import com.dicoding.capstoneproject.ui.report.ReportListFragment

@Suppress("DEPRECATION")
class SectionPagerAdapter(private val context: Context, fm: FragmentManager):
FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        @StringRes
        private val TITLES = intArrayOf(R.string.Report, R.string.Filter, R.string.Bookmark)
    }
    override fun getCount(): Int = 3


    override fun getItem(position: Int): Fragment =
        when(position){
            0 -> ReportListFragment()
            1-> FilterFragment()
            2-> BookmarkFragment()
            else -> Fragment()

        }

    override fun getPageTitle(position: Int): CharSequence? = context.resources.getString(TITLES[position])

}