package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.repositories.Repository
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import javax.inject.Inject


class ScheduleViewModelFactory @Inject constructor(
        private val context: App,
        private val prefs: Prefs,
        private val router: Router,
        private val viewModelStore: ViewModelStore,
        private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getScheduleViewModel(context, prefs, router, repository) as T
    }
}
