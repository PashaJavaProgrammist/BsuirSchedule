package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelStore @Inject constructor() {

    private var groupsViewModel: GroupsViewModel? = null

    fun getGroupsViewModel(context: App,
                           prefs: Prefs,
                           router: Router,
                           repository: Repository): GroupsViewModel {
        return if (groupsViewModel == null) {
            groupsViewModel = GroupsViewModel(context, prefs, router, repository)
            groupsViewModel as GroupsViewModel
        } else {
            groupsViewModel as GroupsViewModel
        }
    }

    private var scheduleViewModel: ScheduleViewModel? = null

    fun getScheduleViewModel(context: App,
                             prefs: Prefs,
                             router: Router,
                             repository: Repository): ScheduleViewModel {
        return if (scheduleViewModel == null) {
            scheduleViewModel = ScheduleViewModel(context, prefs, router, repository)
            scheduleViewModel as ScheduleViewModel
        } else {
            scheduleViewModel as ScheduleViewModel
        }
    }

}