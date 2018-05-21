package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel


class GroupsViewModelFactory(val context: App) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroupsViewModel(context) as T
    }
}
