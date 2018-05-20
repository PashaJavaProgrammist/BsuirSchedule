package com.haretskiy.pavel.bsuirschedule.rest

import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApi {


    @get:GET("groups")
    val allGroupsList: Call<List<Group>>

    @GET("studentGroup/schedule")
    fun getGroupScheduleById(@Query("id") id: Int): Call<ScheduleResponse>

    @GET("studentGroup/schedule")
    fun getGroupScheduleGroupName(@Query("studentGroup") studentGroup: String): Call<ScheduleResponse>

}