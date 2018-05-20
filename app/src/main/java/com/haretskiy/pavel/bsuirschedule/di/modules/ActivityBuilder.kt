package com.haretskiy.pavel.bsuirschedule.di.modules

import com.haretskiy.pavel.bsuirschedule.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun providesMainActivityInjector(): GroupsActivity

}