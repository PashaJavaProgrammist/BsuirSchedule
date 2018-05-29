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
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ScheduleActivity : BaseActivity() {

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    private var numberOfGroup = EMPTY_STRING

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

        initObservers()

        setSupportActionBar(toolbar)

        toolbar_schedule.text = numberOfGroup

        getGroupsLiveDataAndSubscribe(numberOfGroup)

        progress_schedule.visibility = View.VISIBLE
    }

    private fun getGroupsLiveDataAndSubscribe(nameOfGroup: String) {
        scheduleViewModel.loadSchedule(nameOfGroup)
    }

    private fun initObservers() {
        scheduleViewModel.positionLiveData.observe(this, Observer {
            pager.setCurrentItem(it ?: 0, true)
            tabs.setScrollPosition(it ?: 0, 0f, true)
        })

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
        adapter.clear()
        for ((position, schedule) in list.withIndex()) {
            val timeState = scheduleViewModel.selectCurrentDay(schedule.weekDay, position, list.size)
            val fragment = ScheduleFragment()
            fragment.timeState = timeState
            fragment.setSchedule(schedule.schedule)
            adapter.addFragment(fragment, schedule.weekDay)
        }
        adapter.notifyDataSetChanged()
    }

    private fun initViewPager() {
        pager.adapter = adapter
        tabs.setupWithViewPager(pager)

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                fab.show()
            }

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
            scheduleViewModel.startScheduleActivity(numberOfGroup)
        }
    }
}
