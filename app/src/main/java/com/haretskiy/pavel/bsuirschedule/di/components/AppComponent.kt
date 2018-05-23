package com.haretskiy.pavel.bsuirschedule.di.components

import android.app.Application
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.di.modules.ActivityBuilder
import com.haretskiy.pavel.bsuirschedule.di.modules.AppModule
import com.haretskiy.pavel.bsuirschedule.di.modules.MainModule
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(groupsViewModel: GroupsViewModel)

}