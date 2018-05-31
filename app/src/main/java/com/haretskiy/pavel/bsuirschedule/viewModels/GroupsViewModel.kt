package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router

class GroupsViewModel(
        application: App,
        private val prefs: Prefs,
        private val router: Router,
        private val repository: Repository) : AndroidViewModel(application) {

    fun getGroupsGroupsLiveData() = repository.groupsGroupsLiveData
    fun getGroupsProgressLiveData() = repository.groupsProgressLiveData
    fun getGroupsSwipeLiveData() = repository.groupsSwipeLiveData
    fun getGroupsConnectionLiveData() = repository.groupsConnectionLiveData

    fun loadGroupsList(bySwipe: Boolean) {
        repository.loadGroupsList(bySwipe)
    }

    fun search(searchText: String) {
        repository.search(searchText)
    }

    fun startScheduleActivity(name: String) {
        router.startScheduleActivity(name)
    }

    fun saveGroupAsDefault(name: String) {
        prefs.saveDefaultGroup(name)
    }

    fun isDefaultGroupExist() = prefs.getDefaultGroup().isNotEmpty()

    fun startDefaultScheduleActivity() {
        router.startScheduleActivity(prefs.getDefaultGroup())
    }

    fun getGroupsLoadingInProgress() = repository.groupsLoadingInProgress

}