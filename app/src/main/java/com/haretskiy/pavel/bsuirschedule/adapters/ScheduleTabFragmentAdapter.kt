package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.haretskiy.pavel.bsuirschedule.DATE_FORMAT
import com.haretskiy.pavel.bsuirschedule.NUMBER_REGEX
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.toDayOfWeekName
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel.TimeState
import java.text.SimpleDateFormat
import java.util.*

class ScheduleTabFragmentAdapter(manager: FragmentManager,
                                 private var weekDayList: MutableList<String> = mutableListOf(),
                                 private var timeStateList: MutableList<TimeState> = mutableListOf(),
                                 private var scheduleList: MutableList<List<ScheduleUnit>> = mutableListOf()) : FragmentStatePagerAdapter(manager) {

    private val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    private var calendar = Calendar.getInstance()

    override fun getItem(position: Int): Fragment {
        val fragment = ScheduleFragment()
        fragment.timeState = timeStateList[position]
        fragment.setSchedule(scheduleList[position])
        return fragment
    }

    override fun getCount() = scheduleList.size

    override fun getPageTitle(position: Int) = findDayOfWeekByDayIfNeeded(weekDayList[position])

    private fun findDayOfWeekByDayIfNeeded(dateString: String): String {
        val isDate = dateString.contains(Regex(NUMBER_REGEX))
        if (isDate) {
            return try {
                val date = simpleDateFormat.parse(dateString)
                calendar.time = date
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK).toDayOfWeekName()
                "$dateString ($dayOfWeek)"
            } catch (ex: Exception) {
                dateString
            }
        }
        return dateString
    }
}