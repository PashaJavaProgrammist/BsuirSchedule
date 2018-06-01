package com.haretskiy.pavel.bsuirschedule.interactors

import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleInteractor @Inject constructor(private val repository: Repository) {

    fun getScheduleScheduleLiveData() = repository.scheduleScheduleLiveData
    fun getScheduleProgressLiveData() = repository.scheduleProgressLiveData
    fun getScheduleInfoLiveData() = repository.scheduleInfoLiveData
    fun getScheduleSwipeLiveData() = repository.scheduleSwipeLiveData
    fun getScheduleConnectionLiveData() = repository.scheduleConnectionLiveData

    fun loadSchedule(nameOfGroup: String, bySwipe: Boolean, exam: Boolean) = repository.loadSchedule(nameOfGroup, bySwipe, exam)

    fun getScheduleLoadingInProgress() = repository.scheduleLoadingInProgress
}