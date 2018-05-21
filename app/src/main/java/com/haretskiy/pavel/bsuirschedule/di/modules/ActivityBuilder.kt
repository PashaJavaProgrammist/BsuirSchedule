package com.haretskiy.pavel.bsuirschedule.di.modules

import com.haretskiy.pavel.bsuirschedule.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(GroupsActivityModule::class)])
    abstract fun providesMainActivityInjector(): GroupsActivity

}