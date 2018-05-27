package com.haretskiy.pavel.bsuirschedule.di.modules

import android.content.Context
import com.google.gson.GsonBuilder
import com.haretskiy.pavel.bsuirschedule.BASE_URL
import com.haretskiy.pavel.bsuirschedule.rest.JsonLoggingInterceptor
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.GroupStoreImpl
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.ViewModelStore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class MainModule {

    @Provides
    @Singleton
    fun provideRestApi(): RestApi {
        val interceptor = JsonLoggingInterceptor()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupStore(): GroupStore = GroupStoreImpl()

    @Provides
    @Singleton
    fun providePrefs(context: Context): Prefs = Prefs(context)


    @Provides
    @Singleton
    fun provideViewModelStore(): ViewModelStore = ViewModelStore()

}