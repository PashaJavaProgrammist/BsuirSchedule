package com.haretskiy.pavel.bsuirschedule.di.modules

import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.utils.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface AppModule {

    @Binds
    fun provideApplication(app: App): Context

    @Binds
    @Singleton
    fun provideGroupStore(groupStore: GroupStoreImpl): GroupStore

    @Binds
    @Singleton
    fun provideScheduleStore(scheduleStore: ScheduleStoreImpl): ScheduleStore

    @Binds
    @Singleton
    fun provideNetConnectivityManagerImpl(netConnectivityManager: NetConnectivityManagerImpl): NetConnectivityManager
}