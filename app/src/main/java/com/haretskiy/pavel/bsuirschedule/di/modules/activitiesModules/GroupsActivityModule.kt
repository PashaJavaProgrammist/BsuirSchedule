package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.haretskiy.pavel.bsuirschedule.di.modules.AppModule
import com.haretskiy.pavel.bsuirschedule.di.scopes.ActivityScope
import com.haretskiy.pavel.bsuirschedule.ui.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.GroupsViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [AppModule::class])
class GroupsActivityModule {

    @Provides
    @ActivityScope
    fun provideViewModel(provider: ViewModelProvider): GroupsViewModel = provider.get(GroupsViewModel::class.java)

    @Provides
    @ActivityScope
    fun provideViewModelProvider(activity: GroupsActivity, factory: GroupsViewModelFactory): ViewModelProvider = ViewModelProviders.of(activity, factory)

}