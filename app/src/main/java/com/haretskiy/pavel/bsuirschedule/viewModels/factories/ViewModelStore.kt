package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
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
                           restApi: RestApi): GroupsViewModel {
        if (groupsViewModel == null) {
            groupsViewModel = GroupsViewModel(context, groupStore, prefs, router, restApi)
            return groupsViewModel as GroupsViewModel
        } else {
            return groupsViewModel as GroupsViewModel
        }
    }

    private var scheduleViewModel: ScheduleViewModel? = null

    fun getScheduleViewModel(context: App,
                             prefs: Prefs,
                             router: Router,
                             restApi: RestApi): ScheduleViewModel {
        if (scheduleViewModel == null) {
            scheduleViewModel = ScheduleViewModel(context, prefs, router, restApi)
            return scheduleViewModel as ScheduleViewModel
        } else {
            return scheduleViewModel as ScheduleViewModel
        }
    }

}