package com.haretskiy.pavel.bsuirschedule.viewModels.factories

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.rest.RestApi
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.GroupStore
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.Router
import javax.inject.Inject


class GroupsViewModelFactory @Inject constructor(
        private val context: App,
        private val groupStore: GroupStore,
        private val prefs: Prefs,
        private val router: Router,
        private val restApi: RestApi,
        private val viewModelStore: ViewModelStore,
        private val netConnectivityManager: NetConnectivityManager) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModelStore.getGroupsViewModel(context, groupStore, prefs, router, restApi, netConnectivityManager) as T
    }
}

