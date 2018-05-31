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

    val groupsGroupsLiveData = repository.groupsGroupsLiveData
    val groupsProgressLiveData = repository.groupsProgressLiveData
    val groupsSwipeLiveData = repository.groupsSwipeLiveData
    val groupsConnectionLiveData = repository.groupsConnectionLiveData
    val groupsLoadingInProgressLiveData = repository.groupsLoadingInProgressLiveData

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

}