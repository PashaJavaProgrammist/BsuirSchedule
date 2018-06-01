package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.interactors.GroupsInteractor
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import javax.inject.Inject


class GroupsViewModelFactory @Inject constructor(
        private val context: App,
        private val prefs: Prefs,
        private val router: Router,
        private val viewModelStore: ViewModelStore,
        private val groupsInteractor: GroupsInteractor) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getGroupsViewModel(context, prefs, router, groupsInteractor) as T
    }
}

