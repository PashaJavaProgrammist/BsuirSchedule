package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
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

    override fun getResLayout() = R.layout.activity_schedule

    private var numberOfGroup = EMPTY_STRING

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    private val adapter: ScheduleTabFragmentAdapter by lazy {
        ScheduleTabFragmentAdapter(supportFragmentManager)
    }

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
        for (schedule in list) {
            val fragment = ScheduleFragment()
            fragment.setSchedule(schedule.schedule)
            adapter.addFragment(fragment, schedule.weekDay)
        }
        adapter.notifyDataSetChanged()
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
            recreate()
        }
    }
}
