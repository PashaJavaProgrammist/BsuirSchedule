package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import com.haretskiy.pavel.bsuirschedule.ui.activities.SplashActivity
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.utils.RouterImpl
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

    @Provides
    fun provideRouter(activity: SplashActivity): Router = RouterImpl(activity)

}