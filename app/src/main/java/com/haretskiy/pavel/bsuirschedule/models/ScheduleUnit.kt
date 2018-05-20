package com.haretskiy.pavel.bsuirschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING


data class ScheduleUnit(

        @SerializedName("weekNumber")
        @Expose
        var weekNumber: List<Int> = emptyList(),
        @SerializedName("studentGroup")
        @Expose
        var studentGroup: List<String> = emptyList(),
        @SerializedName("numSubgroup")
        @Expose
        var numSubgroup: Int = 0,
        @SerializedName("auditory")
        @Expose
        var auditory: List<String> = emptyList(),
        @SerializedName("lessonTime")
        @Expose
        var lessonTime: String = EMPTY_STRING,
        @SerializedName("startLessonTime")
        @Expose
        var startLessonTime: String = EMPTY_STRING,
        @SerializedName("endLessonTime")
        @Expose
        var endLessonTime: String = EMPTY_STRING,
        @SerializedName("subject")
        @Expose
        var subject: String = EMPTY_STRING,
        @SerializedName("note")
        @Expose
        var note: String = EMPTY_STRING,
        @SerializedName("lessonType")
        @Expose
        var lessonType: String = EMPTY_STRING,
        @SerializedName("employee")
        @Expose
        var employee: List<Employee> = emptyList(),
        @SerializedName("zaoch")
        @Expose
        var zaoch: Boolean = false
)