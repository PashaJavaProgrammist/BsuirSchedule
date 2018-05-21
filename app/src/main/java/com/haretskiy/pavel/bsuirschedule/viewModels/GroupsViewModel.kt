package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.daggerComponent
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import okhttp3.ResponseBody
import javax.inject.Inject

class GroupsViewModel(application: App) : AndroidViewModel(application) {
    init {
        daggerComponent.inject(this)
    }

    @Inject
    lateinit var restApi: RestApi

    val groupsLiveData = MutableLiveData<List<Group>>()

    fun loadGroupsList() {
        restApi.allGroupsList.enqueue(object : BaseCallBack<List<Group>> {
            override fun onError(code: Int?, errorBody: ResponseBody?) {
            }

            override fun onSuccess(response: List<Group>?) {
                if (response != null) groupsLiveData.postValue(response)
            }

            override fun onFailure(t: Throwable) {
            }
        })
    }
}