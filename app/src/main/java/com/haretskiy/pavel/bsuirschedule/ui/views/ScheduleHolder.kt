package com.haretskiy.pavel.bsuirschedule.ui.views

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.haretskiy.pavel.bsuirschedule.*
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity.TimeState
import kotlinx.android.synthetic.main.item_schedule.view.*
import java.util.*

class ScheduleHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindHolder(scheduleUnit: ScheduleUnit, timeState: TimeState) {

        scheduleUnit.also {
            fillAuditory(it)
            fillEmployee(it)
            fillLessonTime(it)
            fillLessonType(it)
            fillNote(it)
            fillSubGroup(it)
            fillSubject(it)
            fillWeekNumber(it)

            colorViewByDay(it, timeState)
        }
    }

    private fun colorViewByDay(scheduleUnit: ScheduleUnit, timeState: TimeState) {
        when (timeState) {
            TimeState.FUTURE -> {
                view.card_view.setCardBackgroundColor(Color.WHITE)
            }
            TimeState.PAST -> {
                view.card_view.setCardBackgroundColor(Color.GRAY)
            }
            TimeState.PRESENT -> colorViewByTime(scheduleUnit)

        }
    }

    private fun colorViewByTime(scheduleUnit: ScheduleUnit) {

        val scheduleStartDate = scheduleUnit.startLessonTime.toTime()
        val schStartH = scheduleStartDate.getH()
        val schStartM = scheduleStartDate.getM()

        val scheduleEndDate = scheduleUnit.endLessonTime.toTime()
        val schEndH = scheduleEndDate.getH()
        val schEndM = scheduleEndDate.getM()

        val curDate = Date(System.currentTimeMillis())
        val curH = curDate.getH()
        val curM = curDate.getM()

        val startMinutes = convertToMinutes(schStartH, schStartM)
        val endMinutes = convertToMinutes(schEndH, schEndM)
        val curMinutes = convertToMinutes(curH, curM)

        when (true) {
            curMinutes < startMinutes -> {
                view.card_view.setCardBackgroundColor(Color.WHITE)
            }
            curMinutes > endMinutes -> {
                view.card_view.setCardBackgroundColor(Color.GRAY)
            }
            curMinutes in (startMinutes + 1)..(endMinutes - 1) -> {
                view.card_view.setCardBackgroundColor(Color.GREEN)
            }
        }
    }

    private fun convertToMinutes(h: Int, m: Int) = h * 60 + m

    private fun fillSubject(scheduleUnit: ScheduleUnit) {
        view.subject.text = scheduleUnit.subject
    }

    private fun fillNote(scheduleUnit: ScheduleUnit) {
        if (scheduleUnit.note.isNotEmpty()) {
            view.note.text = scheduleUnit.note
            view.note.visibility = View.VISIBLE
        } else {
            view.note.visibility = View.GONE
        }
    }

    private fun fillWeekNumber(scheduleUnit: ScheduleUnit) {
        if (scheduleUnit.weekNumber.isNotEmpty()) {
            view.week_number.text = scheduleUnit.weekNumber.toString()
        }
    }

    private fun fillSubGroup(scheduleUnit: ScheduleUnit) {
        if (scheduleUnit.numSubgroup != 0) {
            view.subgroup.text = scheduleUnit.numSubgroup.toString()
        }
    }

    private fun fillLessonType(scheduleUnit: ScheduleUnit) {
        view.type_lesson.text = scheduleUnit.lessonType
    }

    private fun fillLessonTime(scheduleUnit: ScheduleUnit) {
        val lessonTime = "${scheduleUnit.startLessonTime} - ${scheduleUnit.endLessonTime}"
        view.lesson_time.text = lessonTime
    }

    private fun fillEmployee(scheduleUnit: ScheduleUnit) {
        var empStr = EMPTY_STRING
        when {
            scheduleUnit.employee.size > 1 -> {
                for (emp in scheduleUnit.employee) {
                    val employee = "${emp.toPrettyFormat()}\n"
                    empStr += employee
                }
                view.employee.visibility = View.VISIBLE
            }
            scheduleUnit.employee.size == 1 -> {
                view.employee.visibility = View.VISIBLE
                empStr = scheduleUnit.employee[0].toPrettyFormat()
            }
            else -> view.employee.visibility = View.GONE
        }
        view.employee.text = empStr
    }

    private fun fillAuditory(scheduleUnit: ScheduleUnit) {
        var audStr = EMPTY_STRING
        for (aud in scheduleUnit.auditory) {
            val auditory = "$aud, "
            audStr += auditory
        }
        if (audStr.length > 2) {
            audStr.subSequence(0, audStr.length - 2)
            view.auditory.text = audStr
        }
    }

}