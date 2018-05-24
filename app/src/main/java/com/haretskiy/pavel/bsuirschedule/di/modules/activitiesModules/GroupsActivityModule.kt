package com.haretskiy.pavel.bsuirschedule.di.modules.activitiesModules

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
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

    @Provides
    fun provideFactory(context: Context, groupStore: GroupStore, router: Router, restApi: RestApi): GroupsViewModelFactory =
            GroupsViewModelFactory(context as App, groupStore, router, restApi)
}