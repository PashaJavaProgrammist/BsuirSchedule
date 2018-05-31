package com.haretskiy.pavel.bsuirschedule.di.modules

import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import com.haretskiy.pavel.bsuirschedule.repositories.RepositoryImpl
import com.haretskiy.pavel.bsuirschedule.utils.implementations.GroupStoreImpl
import com.haretskiy.pavel.bsuirschedule.utils.implementations.NetConnectivityManagerImpl
import com.haretskiy.pavel.bsuirschedule.utils.implementations.ScheduleStoreImpl
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.ScheduleStore
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

    @Binds
    @Singleton
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository
}