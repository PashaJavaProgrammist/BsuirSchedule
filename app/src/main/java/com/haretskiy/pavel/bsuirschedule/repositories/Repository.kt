package com.haretskiy.pavel.bsuirschedule.repositories

import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.models.Schedule

interface Repository {

    val groupsGroupsLiveData: MutableLiveData<List<Group>>
    val groupsProgressLiveData: MutableLiveData<Boolean>
    val groupsSwipeLiveData: MutableLiveData<Boolean>
    val groupsConnectionLiveData: MutableLiveData<Boolean>
    val groupsLoadingInProgressLiveData: MutableLiveData<Boolean>

    val scheduleScheduleLiveData: MutableLiveData<List<Schedule>>
    val schedulePositionLiveData: MutableLiveData<Int>
    val scheduleProgressLiveData: MutableLiveData<Boolean>
    val scheduleInfoLiveData: MutableLiveData<Boolean>
    val scheduleSwipeLiveData: MutableLiveData<Boolean>
    val scheduleConnectionLiveData: MutableLiveData<Boolean>
    val scheduleLoadingInProgressLiveData: MutableLiveData<Boolean>

    fun loadGroupsList(bySwipe: Boolean)

    fun loadSchedule(nameOfGroup: String, bySwipe: Boolean, exam: Boolean)

    fun search(searchText: String)
}