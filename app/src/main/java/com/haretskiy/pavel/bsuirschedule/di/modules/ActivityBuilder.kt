package com.haretskiy.pavel.bsuirschedule.di.modules

import com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules.GroupsActivityModule
import com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules.ScheduleActivityModule
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import com.haretskiy.pavel.bsuirschedule.ui.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(GroupsActivityModule::class)])
    abstract fun providesGroupsActivityInjector(): GroupsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [(ScheduleActivityModule::class)])
    abstract fun providesScheduleActivityInjector(): ScheduleActivity

}