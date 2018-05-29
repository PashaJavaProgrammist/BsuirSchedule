package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import javax.inject.Inject


class ScheduleViewModelFactory @Inject constructor(
        private var context: App,
        private var prefs: Prefs,
        private var router: Router,
        private var restApi: RestApi,
        private var viewModelStore: ViewModelStore) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getScheduleViewModel(context, prefs, router, restApi) as T
    }
}
