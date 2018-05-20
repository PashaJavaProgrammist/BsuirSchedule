package com.haretskiy.pavel.bsuirschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING

data class Schedule(

        @SerializedName("weekDay")
        @Expose
        var weekDay: String = EMPTY_STRING,
        @SerializedName("schedule")
        @Expose
        var schedule: List<ScheduleUnit> = emptyList()
)