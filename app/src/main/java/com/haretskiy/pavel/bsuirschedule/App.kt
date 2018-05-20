package com.haretskiy.pavel.bsuirschedule

import android.app.Activity
import android.app.Application
import com.haretskiy.pavel.bsuirschedule.di.components.AppComponent
import com.haretskiy.pavel.bsuirschedule.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    internal val daggerComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        daggerComponent.inject(this)
    }
}