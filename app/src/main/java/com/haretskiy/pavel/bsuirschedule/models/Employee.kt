package com.haretskiy.pavel.bsuirschedule.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING

data class Employee(

        @SerializedName("firstName")
        @Expose
        var firstName: String = EMPTY_STRING,
        @SerializedName("lastName")
        @Expose
        var lastName: String = EMPTY_STRING,
        @SerializedName("middleName")
        @Expose
        var middleName: String = EMPTY_STRING,
        @SerializedName("rank")
        @Expose
        var rank: String = EMPTY_STRING,
        @SerializedName("photoLink")
        @Expose
        var photoLink: String = EMPTY_STRING,
        @SerializedName("calendarId")
        @Expose
        var calendarId: String = EMPTY_STRING,
        @SerializedName("academicDepartment")
        @Expose
        var academicDepartment: List<String> = emptyList(),
        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("fio")
        @Expose
        var fio: String = EMPTY_STRING
)