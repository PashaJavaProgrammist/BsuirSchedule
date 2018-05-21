package com.haretskiy.pavel.bsuirschedule.di.components

import android.app.Application
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.di.modules.ActivityBuilder
import com.haretskiy.pavel.bsuirschedule.di.modules.AppModule
import com.haretskiy.pavel.bsuirschedule.di.modules.MainModule
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, MainModule::class, AndroidInjectionModule::class, ActivityBuilder::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)

    fun inject(activity: GroupsActivity)

    fun inject(groupsViewModel: GroupsViewModel)

}