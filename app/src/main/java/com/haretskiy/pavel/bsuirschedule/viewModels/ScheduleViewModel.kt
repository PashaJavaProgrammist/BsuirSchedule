package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import okhttp3.ResponseBody
import javax.inject.Inject

class ScheduleViewModel @Inject constructor(
        application: App,
        private var prefs: Prefs,
        private var router: Router,
        private var restApi: RestApi) : AndroidViewModel(application) {

    val scheduleLiveData = MutableLiveData<List<Schedule>>()

    fun loadSchedule(name: String) {
        restApi.getGroupScheduleGroupName(name).enqueue(object : BaseCallBack<ScheduleResponse> {

            override fun onError(code: Int?, errorBody: ResponseBody?) {
                scheduleLiveData.postValue(emptyList())
            }

            override fun onSuccess(response: ScheduleResponse?) {
                if (response != null) {
                    scheduleLiveData.postValue(
                            when (getExam()) {
                                true -> response.examSchedules
                                false -> response.schedules
                            })
                }
            }

            override fun onFailure(t: Throwable) {
                scheduleLiveData.postValue(emptyList())
            }
        })
    }

    fun startGroupsActivity() {
        router.startGroupsActivity()
    }

    fun setExam(exam: Boolean) {
        prefs.setExam(exam)
    }

    fun getExam() = prefs.getExam()

}