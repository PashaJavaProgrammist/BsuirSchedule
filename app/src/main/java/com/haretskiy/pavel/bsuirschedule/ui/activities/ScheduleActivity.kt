package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.haretskiy.pavel.bsuirschedule.BUNDLE_KEY_NUMBER_GROUP
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.ScheduleTabFragmentAdapter
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel.TimeState
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ScheduleActivity : BaseActivity() {

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    private var numberOfGroup = EMPTY_STRING

    override fun getResLayout() = R.layout.activity_schedule

    override fun onSwipeToRefresh() {
        loadSchedule(numberOfGroup, true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        numberOfGroup = intent.getStringExtra(BUNDLE_KEY_NUMBER_GROUP)

        initViewPager()

        initFab()

        initExamSwitch()

        initObservers()

        initSwipeToRefresh(swipe_to_refresh_sch)

        setSupportActionBar(toolbar)

        toolbar_schedule.text = numberOfGroup

        loadSchedule(numberOfGroup, false)
    }

    private fun loadSchedule(nameOfGroup: String, bySwipe: Boolean) {
        if (!scheduleViewModel.getScheduleLoadingInProgress()) {
            scheduleViewModel.loadSchedule(nameOfGroup, bySwipe)
        }
    }

    private fun initObservers() {
        scheduleViewModel.schedulePositionLiveData.observe(this, Observer {
            pager.setCurrentItem(it ?: 0, true)
            tabs.setScrollPosition(it ?: 0, 0f, true)
        })

        scheduleViewModel.getScheduleScheduleLiveData().observe(this, Observer {
            fillViewPagerAdapter(it ?: emptyList())
            swipeAnimFinish(swipe_to_refresh_sch)
        })

        scheduleViewModel.getScheduleProgressLiveData().observe(this, Observer {
            setProgressVisibility(it ?: false)
        })

        scheduleViewModel.getScheduleInfoLiveData().observe(this, Observer {
            setInfoVisibility(it ?: false)
        })

        scheduleViewModel.getScheduleSwipeLiveData().observe(this, Observer {
            setSwipeAnimVisibility(it ?: false)
        })

        scheduleViewModel.getScheduleConnectionLiveData().observe(this, Observer {
            setNoInternetVisible(it ?: false)
        })
    }

    private fun setNoInternetVisible(visible: Boolean) {
        when (visible) {
            true -> no_internet_image_sc.visibility = View.GONE
            false -> no_internet_image_sc.visibility = View.VISIBLE
        }
    }

    private fun setSwipeAnimVisibility(visible: Boolean) {
        when (visible) {
            true -> swipeAnimStart(swipe_to_refresh_sch)
            false -> swipeAnimFinish(swipe_to_refresh_sch)
        }
    }

    private fun setInfoVisibility(visible: Boolean) {
        info.visibility = when (visible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
        pager.visibility = when (visible) {
            true -> View.GONE
            false -> View.VISIBLE
        }
    }

    private fun setProgressVisibility(visible: Boolean) {
        progress_schedule.visibility = when (visible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    private fun fillViewPagerAdapter(list: List<Schedule>) {

        val timeStateList = mutableListOf<TimeState>()
        val scheduleList = mutableListOf<List<ScheduleUnit>>()
        val weekDayList = mutableListOf<String>()

        for ((position, schedule) in list.withIndex()) {
            timeStateList.add(scheduleViewModel.selectCurrentDay(schedule.weekDay, position, list.size))
            scheduleList.add(schedule.schedule)
            weekDayList.add(schedule.weekDay)
        }

        pager.adapter = ScheduleTabFragmentAdapter(supportFragmentManager, weekDayList, timeStateList, scheduleList)
    }

    private fun initViewPager() {

        setInfoVisibility(false)

        tabs.setupWithViewPager(pager)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int): Unit = fab.show()

            override fun onPageSelected(position: Int) {}
        })
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
            loadSchedule(numberOfGroup, false)
        }
    }
}
