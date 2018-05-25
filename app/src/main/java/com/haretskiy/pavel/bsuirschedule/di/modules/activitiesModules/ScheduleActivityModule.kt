package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.utils.RouterImpl
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.ScheduleViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ScheduleActivityModule {

    @Provides
    fun provideRouter(activity: ScheduleActivity): Router = RouterImpl(activity)

    @Provides
    fun provideViewModel(activity: ScheduleActivity, factory: ScheduleViewModelFactory): ScheduleViewModel =
            ViewModelProviders.of(activity, factory).get(ScheduleViewModel::class.java)

    @Provides
    fun provideFactory(context: Context, router: Router, restApi: RestApi): ScheduleViewModelFactory =
            ScheduleViewModelFactory(context as App, router, restApi)
}