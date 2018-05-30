package com.haretskiy.pavel.bsuirschedule.utils

import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse

interface ScheduleStore {

    fun addGroupSchedule(name: String, schedule: ScheduleResponse)

    fun getGroupSchedule(name: String): ScheduleResponse?

    fun clearAll()

    fun isContains(name: String): Boolean
}