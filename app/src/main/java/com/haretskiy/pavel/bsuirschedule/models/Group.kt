package com.haretskiy.pavel.bsuirschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING


data class Group(

        @SerializedName("name")
        @Expose
        val name: String = EMPTY_STRING,
        @SerializedName("facultyId")
        @Expose
        val facultyId: Int = 0,
        @SerializedName("specialityDepartmentEducationFormId")
        @Expose
        val specialityDepartmentEducationFormId: Int = 0,
        @SerializedName("course")
        @Expose
        val course: Int = 0,
        @SerializedName("id")
        @Expose
        val id: Int = 0,
        @SerializedName("calendarId")
        @Expose
        val calendarId: String = EMPTY_STRING
)