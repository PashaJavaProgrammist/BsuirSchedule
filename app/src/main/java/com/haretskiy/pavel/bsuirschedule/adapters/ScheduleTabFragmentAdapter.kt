package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class ScheduleTabFragmentAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private var mFragmentList = ArrayList<Fragment>()
    private var mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment? {
        return mFragmentList[position]
    }

    override fun getCount() = mFragmentList.size

    fun addFragment(fragment: Fragment, title: String) {
        mFragmentList.add(fragment)
        mFragmentTitleList.add(title)
    }

    override fun getPageTitle(position: Int) = mFragmentTitleList[position]

    fun clear() {
        mFragmentList = ArrayList()
        mFragmentTitleList = ArrayList()
    }

}