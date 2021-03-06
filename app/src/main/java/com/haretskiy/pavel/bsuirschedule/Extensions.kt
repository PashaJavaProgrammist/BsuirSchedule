package com.haretskiy.pavel.bsuirschedule

import android.app.Activity
import com.google.gson.GsonBuilder
import android.arch.lifecycle.AndroidViewModel
import com.google.gson.JsonParser
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

    return if (this.rank.isNullOrEmpty()) {
        "$lastName $firstName $middleName"
    } else {
        "$lastName $firstName $middleName ($rank)"
    }
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

fun Int.toDayOfWeekName(): String{
    return when (this) {
        1 -> "ВС"
        2 -> "ПН"
        3 -> "ВТ"
        4 -> "СР"
        5 -> "ЧТ"
        6 -> "ПТ"
        7 -> "СБ"
        else -> ""
    }
}

fun String.toDate(): Date = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).parse(this)

fun String.toTime(): Date = SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).parse(this)

fun Date.getH(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.getM(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun String.toPrettyFormat(): String {
    return try {
        val parser = JsonParser()
        val json = parser.parse(this).asJsonObject

        val gson = GsonBuilder().setPrettyPrinting().create()

        gson.toJson(json)
    } catch (ex: Exception) {
        this
    }
}

fun Date.isToday(todayDate: Date): Boolean {
    val calendar = Calendar.getInstance()
    calendar.time = todayDate
    val todayYear = calendar.get(Calendar.YEAR)
    val todayMonth = calendar.get(Calendar.MONTH)
    val todayDay = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = this
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return day == todayDay && month == todayMonth && year == todayYear
}