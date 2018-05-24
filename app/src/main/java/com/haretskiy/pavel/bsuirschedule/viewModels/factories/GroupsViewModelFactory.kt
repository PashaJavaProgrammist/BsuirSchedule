package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.Router
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import javax.inject.Inject


class GroupsViewModelFactory @Inject constructor(
        private var context: App,
        private var groupStore: GroupStore,
        private var router: Router,
        private var restApi: RestApi) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GroupsViewModel(context, groupStore, router, restApi) as T
    }
}
