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
import com.haretskiy.pavel.bsuirschedule.utils.Router
import okhttp3.ResponseBody
import java.util.*


class ScheduleViewModel(
        application: App,
        private var prefs: Prefs,
        private var router: Router,
        private var restApi: RestApi) : AndroidViewModel(application) {

    val scheduleLiveData = MutableLiveData<List<Schedule>>()
    val positionLiveData = MutableLiveData<Int>()


    private var listExamSchedule = emptyList<Schedule>()
    private var listSchedule = emptyList<Schedule>()

    private val calendar = Calendar.getInstance()

    private var currentPosition = 0

    fun loadSchedule(name: String) {

        val exam = getExam()

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
            }

            override fun onFailure(t: Throwable) {
                scheduleLiveData.postValue(emptyList())
            }
        })
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
                    currentPosition = if (position + 1 <= listSize) {
                        position + 1
                    } else {
                        position
                    }
                    positionLiveData.postValue(currentPosition)
                    TimeState.PAST
                }
                scheduleDate.isToday(currentDate) -> {
                    currentPosition = position
                    positionLiveData.postValue(currentPosition)
                    TimeState.PRESENT
                }
                else -> {
                    positionLiveData.postValue(currentPosition)
                    TimeState.FUTURE
                }
            }
        } catch (ex: Exception) {
            try {
                val currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
                val scheduleWeekDay = weekDay.toWeekDayNumber()
                return when {
                    scheduleWeekDay < currentWeekDay -> {
                        positionLiveData.postValue(currentPosition)
                        currentPosition = if (position + 1 <= listSize) {
                            position + 1
                        } else {
                            position
                        }
                        TimeState.PAST
                    }
                    scheduleWeekDay == currentWeekDay -> {
                        positionLiveData.postValue(currentPosition)
                        currentPosition = position
                        TimeState.PRESENT
                    }
                    else -> TimeState.FUTURE
                }
            } catch (ex: Exception) {
                //sdfDate error
                positionLiveData.postValue(currentPosition)
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

    fun startScheduleActivity(name: String) {
        router.startScheduleActivity(name)
    }

    enum class TimeState {
        PRESENT, PAST, FUTURE
    }

}