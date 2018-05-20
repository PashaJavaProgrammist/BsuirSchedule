package com.haretskiy.pavel.bsuirschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING

data class ScheduleResponse(

        @SerializedName("employee")
        @Expose
        var employee: Employee = Employee(),
        @SerializedName("studentGroup")
        @Expose
        var studentGroup: Group = Group(),
        @SerializedName("schedules")
        @Expose
        var schedules: List<Schedule> = emptyList(),
        @SerializedName("examSchedules")
        @Expose
        var examSchedules: List<Schedule> = emptyList(),
        @SerializedName("todayDate")
        @Expose
        var todayDate: String = EMPTY_STRING,
        @SerializedName("todaySchedules")
        @Expose
        var todaySchedules: List<ScheduleUnit> = emptyList(),
        @SerializedName("tomorrowDate")
        @Expose
        var tomorrowDate: String = EMPTY_STRING,
        @SerializedName("tomorrowSchedules")
        @Expose
        var tomorrowSchedules: List<ScheduleUnit> = emptyList(),
        @SerializedName("currentWeekNumber")
        @Expose
        var currentWeekNumber: Int = 0
)