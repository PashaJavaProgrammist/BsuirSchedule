package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import android.arch.lifecycle.ViewModelProviders
import com.haretskiy.pavel.bsuirschedule.ui.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.utils.RouterImpl
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.GroupsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class GroupsActivityModule {

    @Provides
    fun provideViewModel(activity: GroupsActivity, factory: GroupsViewModelFactory): GroupsViewModel =
            ViewModelProviders.of(activity, factory).get(GroupsViewModel::class.java)

    @Provides
    fun provideRouter(activity: GroupsActivity): Router = RouterImpl(activity)

}