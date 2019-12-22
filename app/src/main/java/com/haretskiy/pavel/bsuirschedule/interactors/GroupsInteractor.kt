package com.haretskiy.pavel.bsuirschedule.interactors

import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupsInteractor @Inject constructor(private val repository: Repository):Interactor {

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

    fun getGroupsLoadingInProgress() = repository.groupsLoadingInProgress

}