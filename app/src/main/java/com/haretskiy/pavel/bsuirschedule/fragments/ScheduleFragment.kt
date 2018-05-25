package com.haretskiy.pavel.bsuirschedule.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.ScheduleAdapter
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment() {

    val adapter: ScheduleAdapter by lazy {
        ScheduleAdapter(emptyList())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_schedule, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListOfSubjects()
    }

    private fun initListOfSubjects() {
        rv_schedule.layoutManager = LinearLayoutManager(activity)
        rv_schedule.adapter = adapter
    }


    fun setSchedule(schedule: List<ScheduleUnit>) {
        adapter.listOfSchedule = schedule
        adapter.notifyDataSetChanged()
    }

//todo: need to save List<ScheduleUnit>
}
