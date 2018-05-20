package com.haretskiy.pavel.bsuirschedule

import android.app.Application
import com.haretskiy.pavel.bsuirschedule.di.AppComponent
import com.haretskiy.pavel.bsuirschedule.di.DaggerAppComponent

class App : Application() {

    internal val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}