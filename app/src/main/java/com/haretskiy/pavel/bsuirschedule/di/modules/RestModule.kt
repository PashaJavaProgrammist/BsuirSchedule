package com.haretskiy.pavel.bsuirschedule.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.haretskiy.pavel.bsuirschedule.BASE_URL
import com.haretskiy.pavel.bsuirschedule.rest.JsonLoggingInterceptor
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestModule {

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideBuilder(client: OkHttpClient, factory: GsonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(factory)
                    .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: JsonLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory = GsonConverterFactory.create(gson)

}