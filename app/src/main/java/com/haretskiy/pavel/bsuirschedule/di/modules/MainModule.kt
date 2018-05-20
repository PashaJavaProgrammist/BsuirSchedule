package com.haretskiy.pavel.bsuirschedule.di.modules

import com.google.gson.GsonBuilder
import com.haretskiy.pavel.bsuirschedule.BASE_URL
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class MainModule {

    @Provides
    @Singleton
    fun provideRestApi(): RestApi {
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(RestApi::class.java)
    }

}