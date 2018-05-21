package com.haretskiy.pavel.bsuirschedule.di.modules

import android.arch.lifecycle.ViewModelProviders
import com.haretskiy.pavel.bsuirschedule.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import dagger.Module
import dagger.Provides

@Module
class GroupsActivityModule {

    @Provides
    fun provideViewModel(activity: GroupsActivity): GroupsViewModel = ViewModelProviders.of(activity).get(GroupsViewModel::class.java)
}