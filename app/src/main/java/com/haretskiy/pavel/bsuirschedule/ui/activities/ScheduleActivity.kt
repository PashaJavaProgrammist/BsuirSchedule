package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.haretskiy.pavel.bsuirschedule.*
import com.haretskiy.pavel.bsuirschedule.adapters.ScheduleTabFragmentAdapter
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*
import javax.inject.Inject

class ScheduleActivity : BaseActivity() {

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    private val calendar = Calendar.getInstance()

    private var numberOfGroup = EMPTY_STRING

    private var currentPosition = 0

    private val adapter: ScheduleTabFragmentAdapter by lazy {
        ScheduleTabFragmentAdapter(supportFragmentManager)
    }

    override fun getResLayout() = R.layout.activity_schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        info.visibility = View.GONE

        numberOfGroup = intent.getStringExtra(BUNDLE_KEY_NUMBER_GROUP)

        initViewPager()

        initFab()

        initExamSwitch()

        setSupportActionBar(toolbar)

        toolbar_schedule.text = numberOfGroup

        getGroupsLiveDataAndSubscribe(numberOfGroup)

        progress_schedule.visibility = View.VISIBLE
    }

    private fun getGroupsLiveDataAndSubscribe(nameOfGroup: String) {
        scheduleViewModel.loadSchedule(nameOfGroup)

        scheduleViewModel.scheduleLiveData.observe(this, Observer {
            if (it != null && it.isNotEmpty()) {
                fillViewPagerAdapter(it)
                progress_schedule.visibility = View.GONE
                info.visibility = View.GONE
            } else {
                progress_schedule.visibility = View.GONE
                info.visibility = View.VISIBLE
            }
        })

    }

    private fun fillViewPagerAdapter(list: List<Schedule>) {

        for ((i, schedule) in list.withIndex()) {
            val fragment = ScheduleFragment()
            fragment.setSchedule(schedule.schedule)
            adapter.addFragment(fragment, schedule.weekDay)
            selectCurrentDay(schedule.weekDay, i, list.size)
        }
        adapter.notifyDataSetChanged()
        pager.currentItem = currentPosition
    }

    private fun selectCurrentDay(weekDay: String, position: Int, listSize: Int) {

        try {
            val scheduleDate = weekDay.toDate()
            when {
                scheduleDate < calendar.time -> currentPosition = if (position + 1 <= listSize) {
                    position + 1
                } else {
                    position
                }
                scheduleDate == calendar.time -> currentPosition = position
            }
        } catch (ex: Exception) {
            try {
                val currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
                val scheduleWeekDay = weekDay.toWeekDayNumber()
                when {
                    scheduleWeekDay < currentWeekDay -> currentPosition = if (position + 1 <= listSize) {
                        position + 1
                    } else {
                        position
                    }
                    scheduleWeekDay == currentWeekDay -> currentPosition = position
                }
            } catch (ex: Exception) {
                //sdfDate error
            }
        }

    }

    private fun initViewPager() {
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)
    }

    private fun initFab() {
        fab.setOnClickListener {
            scheduleViewModel.startGroupsActivity()
        }
    }

    private fun initExamSwitch() {
        exam_switch.isChecked = scheduleViewModel.getExam()
        exam_switch.setOnCheckedChangeListener { _, isChecked ->
            scheduleViewModel.setExam(isChecked)
            scheduleViewModel.startScheduleActivity(numberOfGroup)
        }
    }
}
