package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.*
import com.haretskiy.pavel.bsuirschedule.models.Schedule
import com.haretskiy.pavel.bsuirschedule.models.ScheduleResponse
import com.haretskiy.pavel.bsuirschedule.rest.BaseCallBack
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import okhttp3.ResponseBody
import java.util.*


class ScheduleViewModel(
        application: App,
        private val prefs: Prefs,
        private val router: Router,
        private val restApi: RestApi) : AndroidViewModel(application) {

    val scheduleLiveData = MutableLiveData<List<Schedule>>()
    val positionLiveData = MutableLiveData<Int>()


    private var listExamSchedule = emptyList<Schedule>()
    private var listSchedule = emptyList<Schedule>()

    var nameOfGroup = EMPTY_STRING

    private val calendar = Calendar.getInstance()

    private var todayPosition = 0

    fun loadSchedule(name: String) {

        val exam = getExam()

        if (name != nameOfGroup) {

            restApi.getGroupScheduleGroupName(name).enqueue(object : BaseCallBack<ScheduleResponse> {

                override fun onError(code: Int?, errorBody: ResponseBody?) {
                    scheduleLiveData.postValue(emptyList())
                }

                override fun onSuccess(response: ScheduleResponse?) {
                    if (response != null) {
                        scheduleLiveData.postValue(
                                when (exam) {
                                    true -> saveSchedule(true, response.examSchedules)
                                    false -> saveSchedule(false, response.schedules)
                                })
                    }
                    nameOfGroup = name
                }

                override fun onFailure(t: Throwable) {
                    scheduleLiveData.postValue(emptyList())
                }
            })
        } else {
            scheduleLiveData.postValue(
                    when (exam) {
                        true -> listExamSchedule
                        false -> listSchedule
                    })
        }
    }

    fun saveSchedule(exam: Boolean, list: List<Schedule>): List<Schedule> {
        when (exam) {
            true -> listExamSchedule = list
            false -> listSchedule = list
        }
        return list
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