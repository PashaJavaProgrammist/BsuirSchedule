package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.interactors.GroupsInteractor
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router

class GroupsViewModel(
        application: App,
        private val prefs: Prefs,
        private val router: Router,
        private val groupsInteractor: GroupsInteractor) : AndroidViewModel(application) {

    fun getGroupsGroupsLiveData() = groupsInteractor.getGroupsGroupsLiveData()
    fun getGroupsProgressLiveData() = groupsInteractor.getGroupsProgressLiveData()
    fun getGroupsSwipeLiveData() = groupsInteractor.getGroupsSwipeLiveData()
    fun getGroupsConnectionLiveData() = groupsInteractor.getGroupsConnectionLiveData()

    fun loadGroupsList(bySwipe: Boolean) {
        groupsInteractor.loadGroupsList(bySwipe)
    }

    fun search(searchText: String) {
        groupsInteractor.search(searchText)
    }

    fun startScheduleActivity(name: String) {
        router.startScheduleActivity(name)
    }

    fun saveGroupAsDefault(name: String) {
        prefs.defaultGroupName = name
    }

    fun isDefaultGroupExist() = prefs.defaultGroupName.isNotEmpty()

    fun startDefaultScheduleActivity() {
        router.startScheduleActivity(prefs.defaultGroupName)
    }

    fun getGroupsLoadingInProgress() = groupsInteractor.getGroupsLoadingInProgress()

}