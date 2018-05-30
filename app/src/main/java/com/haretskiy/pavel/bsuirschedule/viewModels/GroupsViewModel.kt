package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import okhttp3.ResponseBody

class GroupsViewModel(
        application: App,
        private val groupStore: GroupStore,
        private val prefs: Prefs,
        private val router: Router,
        private val restApi: RestApi) : AndroidViewModel(application) {

    val groupsLiveData = MutableLiveData<List<Group>>()

    fun loadGroupsList(bySwipe: Boolean) {
        if (groupStore.getListSize() != 0 && !bySwipe) {
            groupsLiveData.postValue(groupStore.getList())
        } else {
            restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
                override fun onError(code: Int?, errorBody: ResponseBody?) {
                    groupsLiveData.postValue(emptyList())
                }

                override fun onSuccess(response: List<Group>?) {
                    if (response != null) {
                        groupStore.saveList(response)
                        groupsLiveData.postValue(response)
                    } else {
                        groupsLiveData.postValue(emptyList())
                    }
                }

                override fun onFailure(t: Throwable) {
                    groupsLiveData.postValue(emptyList())
                }
            })
        }
    }

    fun startScheduleActivity(name: String) {
        router.startScheduleActivity(name)
    }

    override fun onCleared() {
        super.onCleared()
        groupStore.clearList()
    }

    fun search(searchText: String) {
        val list = arrayListOf<Group>()

        for (group in groupStore.getList()) {
            if (group.name.contains(searchText)) {
                list.add(group)
            }
        }

        groupsLiveData.postValue(list)
    }

    fun saveGroupAsDefault(name: String) {
        prefs.saveDefaultGroup(name)
    }

    fun isDefaultGroupExist() = prefs.getDefaultGroup().isNotEmpty()

    fun startDefaultScheduleActivity() {
        router.startScheduleActivity(prefs.getDefaultGroup())
    }

}