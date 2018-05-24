package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Router
import okhttp3.ResponseBody
import javax.inject.Inject

class GroupsViewModel @Inject constructor(
        application: App,
        private var router: Router,
        private var restApi: RestApi) : AndroidViewModel(application) {

    var listOfGroups: List<Group> = emptyList()

    val groupsLiveData = MutableLiveData<List<Group>>()

    fun loadGroupsList() {
        if (listOfGroups.isNotEmpty()) {
            groupsLiveData.postValue(listOfGroups)
        } else {
            restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
                override fun onError(code: Int?, errorBody: ResponseBody?) {
                    groupsLiveData.postValue(emptyList())
                }

                override fun onSuccess(response: List<Group>?) {
                    if (response != null) {
                        listOfGroups = response
                        groupsLiveData.postValue(response)
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
}