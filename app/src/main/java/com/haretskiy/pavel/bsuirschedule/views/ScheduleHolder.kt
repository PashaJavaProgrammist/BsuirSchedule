package com.haretskiy.pavel.bsuirschedule.views

import android.support.v7.widget.RecyclerView
import android.view.View
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import kotlinx.android.synthetic.main.item_schedule.view.*

class ScheduleHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindHolder(scheduleUnit: ScheduleUnit) {

        var audStr = EMPTY_STRING
        for (aud in scheduleUnit.auditory) {
            val auditory = "$aud, "
            audStr += auditory
        }
        if (audStr.length > 2) {
            audStr.subSequence(0, audStr.length - 2)
            view.auditory.text = audStr
        }
        var empStr = EMPTY_STRING
        for (emp in scheduleUnit.employee) {
            val employee = "$emp,\n"
            empStr += employee
        }
        view.employee.text = empStr

        val lessonTime = "${scheduleUnit.startLessonTime} - ${scheduleUnit.endLessonTime}"
        view.lesson_time.text = lessonTime

        view.type_lesson.text = scheduleUnit.lessonType

        view.note.text = scheduleUnit.note

        if (!scheduleUnit.numSubgroup.equals(0)) {
            view.subgroup.text = scheduleUnit.numSubgroup.toString()
        }

        if (scheduleUnit.weekNumber.isNotEmpty()) {
            view.note.text = scheduleUnit.weekNumber.toString()
        }
        view.subject.text = scheduleUnit.subject

    }

}