package com.haretskiy.pavel.bsuirschedule

import android.app.Activity
import android.arch.lifecycle.AndroidViewModel
import com.haretskiy.pavel.bsuirschedule.di.components.AppComponent
import com.haretskiy.pavel.bsuirschedule.models.Employee
import java.text.SimpleDateFormat
import java.util.*

val Activity.daggerComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }

val AndroidViewModel.daggerComponent: AppComponent
    get() {
        return (getApplication() as App).daggerComponent
    }


fun Employee.toPrettyFormat(): String {
    val lastName = this.lastName
    val firstName = this.firstName
    val middleName = this.middleName

    return "$lastName $firstName $middleName"
}

fun String.toWeekDayNumber(): Int {
    return when (this.toLowerCase()) {
        "понедельник" -> 2
        "вторник" -> 3
        "среда" -> 4
        "четверг" -> 5
        "пятница" -> 6
        "суббота" -> 7
        "воскресение" -> 1
        "sunday" -> 1
        "monday" -> 2
        "tuesday" -> 3
        "wednesday" -> 4
        "thursday" -> 5
        "friday" -> 6
        "saturday" -> 7
        else -> 0
    }
}

fun String.toDate(): Date = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(this)