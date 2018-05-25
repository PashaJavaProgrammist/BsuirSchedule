package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.views.ScheduleHolder

class ScheduleAdapter(var listOfSchedule: List<Group>) : RecyclerView.Adapter<ScheduleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)
        return ScheduleHolder(view)
    }

    override fun getItemCount() = listOfSchedule.size

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        holder.bindHolder(listOfSchedule[position])
    }


}