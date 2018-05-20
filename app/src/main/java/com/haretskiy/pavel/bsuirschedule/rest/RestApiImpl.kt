package com.haretskiy.pavel.bsuirschedule.rest

import com.google.gson.GsonBuilder
import com.haretskiy.pavel.bsuirschedule.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RestApiImpl {

    fun getARestApi(): RestApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(RestApi::class.java)
    }

}