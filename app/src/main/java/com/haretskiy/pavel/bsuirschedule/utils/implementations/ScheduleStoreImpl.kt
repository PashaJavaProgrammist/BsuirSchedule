package com.haretskiy.pavel.bsuirschedule.utils.implementations

import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.ScheduleStore
import javax.inject.Inject

class ScheduleStoreImpl @Inject constructor() : ScheduleStore {

    private val scheduleMap = HashMap<String, ScheduleResponse>()

    override fun addGroupSchedule(name: String, schedule: ScheduleResponse) {
        scheduleMap.put(name, schedule)
    }

    override fun getGroupSchedule(name: String) = scheduleMap[name]

    override fun clearAll(): Unit = scheduleMap.clear()

    override fun isContains(name: String): Boolean = scheduleMap.containsKey(name)
}