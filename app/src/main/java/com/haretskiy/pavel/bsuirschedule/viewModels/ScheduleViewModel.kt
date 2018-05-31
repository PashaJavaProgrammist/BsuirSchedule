package com.haretskiy.pavel.bsuirschedule.viewModels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.isToday
import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import com.haretskiy.pavel.bsuirschedule.toDate
import com.haretskiy.pavel.bsuirschedule.toWeekDayNumber
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import java.util.*

class ScheduleViewModel(
        application: App,
        private val prefs: Prefs,
        private val router: Router,
        private val repository: Repository) : AndroidViewModel(application) {

    fun getScheduleScheduleLiveData() = repository.scheduleScheduleLiveData
    val schedulePositionLiveData = MutableLiveData<Int>()
    fun getScheduleProgressLiveData() = repository.scheduleProgressLiveData
    fun getScheduleInfoLiveData() = repository.scheduleInfoLiveData
    fun getScheduleSwipeLiveData() = repository.scheduleSwipeLiveData
    fun getScheduleConnectionLiveData() = repository.scheduleConnectionLiveData

    private val calendar = Calendar.getInstance()

    private var todayPosition = 0

    fun loadSchedule(nameOfGroup: String, bySwipe: Boolean) {
        repository.loadSchedule(nameOfGroup, bySwipe, getExam())
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
                    schedulePositionLiveData.postValue(todayPosition)
                    TimeState.PAST
                }
                scheduleDate.isToday(currentDate) -> {
                    todayPosition = position
                    schedulePositionLiveData.postValue(todayPosition)
                    TimeState.PRESENT
                }
                else -> {
                    schedulePositionLiveData.postValue(todayPosition)
                    TimeState.FUTURE
                }
            }
        } catch (ex: Exception) {
            try {
                val currentWeekDay = calendar.get(Calendar.DAY_OF_WEEK)
                val scheduleWeekDay = weekDay.toWeekDayNumber()
                return when {
                    scheduleWeekDay < currentWeekDay -> {
                        schedulePositionLiveData.postValue(todayPosition)
                        todayPosition = if (position + 1 <= listSize) {
                            position + 1
                        } else {
                            position
                        }
                        TimeState.PAST
                    }
                    scheduleWeekDay == currentWeekDay -> {
                        schedulePositionLiveData.postValue(todayPosition)
                        todayPosition = position
                        TimeState.PRESENT
                    }
                    else -> TimeState.FUTURE
                }
            } catch (ex: Exception) {
                //sdfDate error
                schedulePositionLiveData.postValue(todayPosition)
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

    fun getScheduleLoadingInProgress() = repository.scheduleLoadingInProgress

    enum class TimeState {
        PRESENT, PAST, FUTURE
    }

}