package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.ScheduleStore
import javax.inject.Inject


class ScheduleViewModelFactory @Inject constructor(
        private val context: App,
        private val prefs: Prefs,
        private val router: Router,
        private val restApi: RestApi,
        private val scheduleStore: ScheduleStore,
        private val viewModelStore: ViewModelStore,
        private val netConnectivityManager: NetConnectivityManager) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getScheduleViewModel(context, prefs, router, restApi, scheduleStore, netConnectivityManager) as T
    }
}
