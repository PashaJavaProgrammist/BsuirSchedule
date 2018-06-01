package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import javax.inject.Inject


class GroupsViewModelFactory @Inject constructor(
        private val router: Router,
        private val viewModelStore: ViewModelStore) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getGroupsViewModel(router) as T
    }
}

