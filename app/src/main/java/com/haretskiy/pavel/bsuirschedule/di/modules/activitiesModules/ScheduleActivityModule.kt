package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import com.haretskiy.pavel.bsuirschedule.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.utils.RouterImpl
import dagger.Module
import dagger.Provides

@Module
class ScheduleActivityModule {

    @Provides
    fun provideRouter(activity: GroupsActivity): Router = RouterImpl(activity)
}