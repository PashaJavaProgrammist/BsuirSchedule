package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.isToday
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.toDate
import com.haretskiy.pavel.bsuirschedule.toWeekDayNumber
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.ScheduleStore
import okhttp3.ResponseBody
import java.util.*


class ScheduleViewModel(
        application: App,
        private val prefs: Prefs,
        private val router: Router,
        private val restApi: RestApi,
        private val scheduleStore: ScheduleStore,
        private val netConnectivityManager: NetConnectivityManager) : AndroidViewModel(application) {

    val scheduleLiveData = MutableLiveData<List<Schedule>>()
    val positionLiveData = MutableLiveData<Int>()
    val progressLiveData = MutableLiveData<Boolean>()
    val infoLiveData = MutableLiveData<Boolean>()
    val swipeLiveData = MutableLiveData<Boolean>()
    val connectionLiveData = MutableLiveData<Boolean>()

    private val calendar = Calendar.getInstance()

    private var todayPosition = 0

    var isLoadingInProgress = false

    fun loadSchedule(nameOfGroup: String, bySwipe: Boolean) {

        isLoadingInProgress = true

        progressLiveData.postValue(!bySwipe)
        swipeLiveData.postValue(bySwipe)

        val exam = getExam()

        if (scheduleStore.isContains(nameOfGroup) && !bySwipe) {
            onSuccessLoadingGroup(exam, scheduleStore.getGroupSchedule(nameOfGroup))
        } else {
            loadFromServer(nameOfGroup, exam, bySwipe)
        }
        connectionLiveData.postValue(netConnectivityManager.hasConnection())
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

            scheduleLiveData.postValue(list)
            infoLiveData.postValue(list.isEmpty())
        }
        swipeLiveData.postValue(false)
        progressLiveData.postValue(false)
        isLoadingInProgress = false
    }

    private fun onFailureLoadingGroup() {
        infoLiveData.postValue(false)
        scheduleLiveData.postValue(emptyList())
        progressLiveData.postValue(false)
        swipeLiveData.postValue(false)
        isLoadingInProgress = false
    }

    fun selectCurrentDay(weekDay: String, position: Int, listSize: Int): TimeState {

        try {
            val scheduleDate: Date = weekDay.toDate()
            val currentDate: Date = calendar.time

            return when {
                scheduleDate.before(currentDate) && !scheduleDate.isToday(currentDate) -> {
                    todayPosition = if (position + 1 <= listSize) {
                        position + 1
                    } else {
                        position
                    }
                    positionLiveData.postValue(todayPosition)
                    TimeState.PAST
                }
                scheduleDate.isToday(currentDate) -> {
                    todayPosition = position
                    positionLiveData.postValue(todayPosition)
                    TimeState.PRESENT
                }
                else -> {
                    positionLiveData.postValue(todayPosition)
                    TimeState.FUTURE
                }
            }
        } catch (ex: Exception) {
            try {
                val currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
                val scheduleWeekDay = weekDay.toWeekDayNumber()
                return when {
                    scheduleWeekDay < currentWeekDay -> {
                        positionLiveData.postValue(todayPosition)
                        todayPosition = if (position + 1 <= listSize) {
                            position + 1
                        } else {
                            position
                        }
                        TimeState.PAST
                    }
                    scheduleWeekDay == currentWeekDay -> {
                        positionLiveData.postValue(todayPosition)
                        todayPosition = position
                        TimeState.PRESENT
                    }
                    else -> TimeState.FUTURE
                }
            } catch (ex: Exception) {
                //sdfDate error
                positionLiveData.postValue(todayPosition)
                return TimeState.FUTURE
            }
        }
    }

    fun startGroupsActivity() {
        router.startGroupsActivity()
    }

    fun setExam(exam: Boolean) {
        prefs.setExam(exam)
    }

    fun getExam() = prefs.getExam()

    enum class TimeState {
        PRESENT, PAST, FUTURE
    }

}