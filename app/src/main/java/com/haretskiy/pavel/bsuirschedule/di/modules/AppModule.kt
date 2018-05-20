package com.haretskiy.pavel.bsuirschedule.di.modules

import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import dagger.Binds
import dagger.Module

@Module
interface AppModule {

    @Binds
    fun provideApplication(app: App): Context
}