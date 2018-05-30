package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.haretskiy.pavel.bsuirschedule.di.modules.AppModule
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.ScheduleViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule::class])
class ScheduleActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModel(provider: ViewModelProvider): ScheduleViewModel = provider.get(ScheduleViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideViewModelProvider(activity: ScheduleActivity, factory: ScheduleViewModelFactory): ViewModelProvider = ViewModelProviders.of(activity, factory)

}