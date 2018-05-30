package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.*
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelStore @Inject constructor() {

    private var groupsViewModel: GroupsViewModel? = null

    fun getGroupsViewModel(context: App,
                           groupStore: GroupStore,
                           prefs: Prefs,
                           router: Router,
                           restApi: RestApi,
                           netConnectivityManager: NetConnectivityManager): GroupsViewModel {
        return if (groupsViewModel == null) {
            groupsViewModel = GroupsViewModel(context, groupStore, prefs, router, restApi, netConnectivityManager)
            groupsViewModel as GroupsViewModel
        } else {
            groupsViewModel as GroupsViewModel
        }
    }

    private var scheduleViewModel: ScheduleViewModel? = null

    fun getScheduleViewModel(context: App,
                             prefs: Prefs,
                             router: Router,
                             restApi: RestApi,
                             scheduleStore: ScheduleStore,
                             netConnectivityManager: NetConnectivityManager): ScheduleViewModel {
        return if (scheduleViewModel == null) {
            scheduleViewModel = ScheduleViewModel(context, prefs, router, restApi, scheduleStore, netConnectivityManager)
            scheduleViewModel as ScheduleViewModel
        } else {
            scheduleViewModel as ScheduleViewModel
        }
    }

}