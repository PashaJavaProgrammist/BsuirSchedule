package com.haretskiy.pavel.bsuirschedule.repositories

import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.ScheduleStore
import okhttp3.ResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val restApi: RestApi,
                                         private val groupStore: GroupStore,
                                         private val scheduleStore: ScheduleStore,
                                         private val netConnectivityManager: NetConnectivityManager) : Repository {

    override val groupsGroupsLiveData = MutableLiveData<List<Group>>()
    override val groupsProgressLiveData = MutableLiveData<Boolean>()
    override val groupsSwipeLiveData = MutableLiveData<Boolean>()
    override val groupsConnectionLiveData = MutableLiveData<Boolean>()

    override var groupsLoadingInProgress = false

    override val scheduleScheduleLiveData = MutableLiveData<List<Schedule>>()
    override val schedulePositionLiveData = MutableLiveData<Int>()
    override val scheduleProgressLiveData = MutableLiveData<Boolean>()
    override val scheduleInfoLiveData = MutableLiveData<Boolean>()
    override val scheduleSwipeLiveData = MutableLiveData<Boolean>()
    override val scheduleConnectionLiveData = MutableLiveData<Boolean>()

    override var scheduleLoadingInProgress = false

    override fun loadGroupsList(bySwipe: Boolean) {

        groupsLoadingInProgress = true
        groupsSwipeLiveData.postValue(bySwipe)
        groupsProgressLiveData.postValue(!bySwipe)

        if (groupStore.isNotEmpty() && !bySwipe) {
            groupsGroupsLiveData.postValue(groupStore.getList())
            groupsProgressLiveData.postValue(false)
            groupsSwipeLiveData.postValue(false)
            groupsLoadingInProgress = false
        } else {
            groupStore.clearList()
            restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
                override fun onError(code: Int?, errorBody: ResponseBody?) {
                    groupsGroupsLiveData.postValue(emptyList())
                    groupsSwipeLiveData.postValue(false)
                    groupsProgressLiveData.postValue(false)
                    groupsLoadingInProgress = false
                }

                override fun onSuccess(response: List<Group>?) {
                    if (response != null) {
                        groupStore.saveList(response)
                        groupsGroupsLiveData.postValue(response)
                    } else {
                        groupsGroupsLiveData.postValue(emptyList())
                    }
                    groupsSwipeLiveData.postValue(false)
                    groupsProgressLiveData.postValue(false)
                    groupsLoadingInProgress = false
                }

                override fun onFailure(t: Throwable) {
                    groupsGroupsLiveData.postValue(emptyList())
                    groupsSwipeLiveData.postValue(false)
                    groupsProgressLiveData.postValue(false)
                    groupsLoadingInProgress = false
                }
            })
        }
        groupsConnectionLiveData.postValue(netConnectivityManager.hasConnection())
    }

    override fun search(searchText: String) {
        groupsLoadingInProgress = true
        val list = arrayListOf<Group>()

        for (group in groupStore.getList()) {
            if (group.name.contains(searchText)) {
                list.add(group)
            }
        }
        groupsGroupsLiveData.postValue(list)
        groupsLoadingInProgress = false
    }

    override fun loadSchedule(nameOfGroup: String, bySwipe: Boolean, exam: Boolean) {
        scheduleLoadingInProgress = true

        scheduleProgressLiveData.postValue(!bySwipe)
        scheduleSwipeLiveData.postValue(bySwipe)

        if (scheduleStore.isContains(nameOfGroup) && !bySwipe) {
            onSuccessLoadingGroup(exam, scheduleStore.getGroupSchedule(nameOfGroup))
        } else {
            loadFromServer(nameOfGroup, exam, bySwipe)
        }
        scheduleConnectionLiveData.postValue(netConnectivityManager.hasConnection())
    }

    private fun loadFromServer(nameOfGroup: String, exam: Boolean, bySwipe: Boolean) {
        restApi.getGroupScheduleGroupName(nameOfGroup).enqueue(object : BaseCallBack<ScheduleResponse> {

            override fun onError(code: Int?, errorBody: ResponseBody?) {
                onFailureLoadingGroup()
            }

            override fun onSuccess(response: ScheduleResponse?) {
                onSuccessLoadingGroup(exam, response)
                if (!scheduleStore.isContains(nameOfGroup) && response != null || bySwipe && response != null) {
                    scheduleStore.addGroupSchedule(nameOfGroup, response)
                }
            }

            override fun onFailure(t: Throwable) {
                onFailureLoadingGroup()
            }
        })
    }

    private fun onSuccessLoadingGroup(exam: Boolean, response: ScheduleResponse?) {
        if (response != null) {

            val list = when (exam) {
                true -> response.examSchedules
                false -> response.schedules
            }

            scheduleScheduleLiveData.postValue(list)
            scheduleInfoLiveData.postValue(list.isEmpty())
        }
        scheduleSwipeLiveData.postValue(false)
        scheduleProgressLiveData.postValue(false)
        scheduleLoadingInProgress = false
    }

    private fun onFailureLoadingGroup() {
        scheduleInfoLiveData.postValue(false)
        scheduleScheduleLiveData.postValue(emptyList())
        scheduleProgressLiveData.postValue(false)
        scheduleSwipeLiveData.postValue(false)
        scheduleLoadingInProgress = false
    }
}