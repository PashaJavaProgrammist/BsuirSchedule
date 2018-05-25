package com.haretskiy.pavel.bsuirschedule

import android.app.Activity
import android.arch.lifecycle.AndroidViewModel
import com.haretskiy.pavel.bsuirschedule.di.components.AppComponent
import com.haretskiy.pavel.bsuirschedule.models.Employee

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