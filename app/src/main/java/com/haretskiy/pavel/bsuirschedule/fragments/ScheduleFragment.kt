package com.haretskiy.pavel.bsuirschedule.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit

class ScheduleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    fun setSchedule(schedule: List<ScheduleUnit>) {

    }


}
