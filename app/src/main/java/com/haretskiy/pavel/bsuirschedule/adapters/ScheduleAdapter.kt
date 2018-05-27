package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity.TimeState
import com.haretskiy.pavel.bsuirschedule.ui.views.ScheduleHolder

class ScheduleAdapter(var listOfSchedule: List<ScheduleUnit>) : RecyclerView.Adapter<ScheduleHolder>() {

    lateinit var timeState: TimeState

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleHolder(view)
    }

    override fun getItemCount() = listOfSchedule.size

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        holder.bindHolder(listOfSchedule[position], timeState)
    }


}