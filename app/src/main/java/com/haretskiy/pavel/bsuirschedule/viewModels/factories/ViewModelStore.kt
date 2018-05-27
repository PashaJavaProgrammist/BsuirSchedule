package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel

class ViewModelStore {

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

}