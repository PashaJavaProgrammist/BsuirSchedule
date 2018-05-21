package com.haretskiy.pavel.bsuirschedule

import android.app.Activity
import android.arch.lifecycle.AndroidViewModel
import com.haretskiy.pavel.bsuirschedule.di.components.AppComponent

val Activity.daggerComponent: AppComponent
    get() {
        return (applicationContext as App).daggerComponent
    }

val AndroidViewModel.daggerComponent: AppComponent
    get() {
        return (getApplication() as App).daggerComponent
    }