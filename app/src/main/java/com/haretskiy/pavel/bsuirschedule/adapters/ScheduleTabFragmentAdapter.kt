package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel.TimeState

class ScheduleTabFragmentAdapter(manager: FragmentManager,
                                 private var weekDayList: MutableList<String> = mutableListOf(),
                                 private var timeStateList: MutableList<TimeState> = mutableListOf(),
                                 private var scheduleList: MutableList<List<ScheduleUnit>> = mutableListOf()) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        val fragment = ScheduleFragment()
        fragment.timeState = timeStateList[position]
        fragment.setSchedule(scheduleList[position])
        return fragment
    }

    override fun getCount() = scheduleList.size

    override fun getPageTitle(position: Int) = weekDayList[position]

}