package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel.TimeState

class ScheduleTabFragmentAdapter(manager: FragmentManager) : FragmentStatePagerAdapter(manager) {

    private var timeStateList = mutableListOf<ScheduleViewModel.TimeState>()
    private var scheduleList = mutableListOf<List<ScheduleUnit>>()
    private var weekDayList = mutableListOf<String>()

    override fun getItem(position: Int): Fragment {
        val fragment = ScheduleFragment()
        fragment.timeState = timeStateList[position]
        fragment.setSchedule(scheduleList[position])
        return fragment
    }

    override fun getCount() = scheduleList.size

    override fun getPageTitle(position: Int) = weekDayList[position]

    fun clear() {
        timeStateList.clear()
        scheduleList.clear()
        weekDayList.clear()
        notifyDataSetChanged()
    }

    fun setContent(timeStateList: MutableList<TimeState>, scheduleList: MutableList<List<ScheduleUnit>>, weekDayList: MutableList<String>) {
        this.timeStateList = timeStateList
        this.scheduleList = scheduleList
        this.weekDayList = weekDayList
        notifyDataSetChanged()
    }

}