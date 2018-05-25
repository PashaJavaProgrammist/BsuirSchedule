package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import com.haretskiy.pavel.bsuirschedule.BUNDLE_KEY_NUMBER_GROUP
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.ScheduleTabFragmentAdapter
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.ui.fragments.ScheduleFragment
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import kotlinx.android.synthetic.main.activity_schedule.*
import javax.inject.Inject

class ScheduleActivity : BaseActivity() {

    override fun getResLayout() = R.layout.activity_schedule

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    val adapter: ScheduleTabFragmentAdapter by lazy {
        ScheduleTabFragmentAdapter(supportFragmentManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val numberOfGroup = intent.getStringExtra(BUNDLE_KEY_NUMBER_GROUP)

        initViewPager()

        initFab()

        getGroupsLiveDataAndSubscribe(numberOfGroup, true)

        progress_schedule.visibility = View.VISIBLE
    }

    private fun getGroupsLiveDataAndSubscribe(nameOfGroup: String, exam: Boolean) {
        scheduleViewModel.loadSchedule(nameOfGroup, exam)

        scheduleViewModel.scheduleLiveData.observe(this, Observer {
            if (it != null) {
                fillViewPagerAdapter(it)
                progress_schedule.visibility = View.GONE
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
}
