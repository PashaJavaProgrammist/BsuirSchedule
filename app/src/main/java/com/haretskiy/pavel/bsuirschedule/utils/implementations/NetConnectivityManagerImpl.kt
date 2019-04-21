package com.haretskiy.pavel.bsuirschedule.utils.implementations

import android.content.Context
import android.net.ConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import javax.inject.Inject


class NetConnectivityManagerImpl @Inject constructor(private val context: Context) : NetConnectivityManager {

    override fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return when {
            activeNetwork != null -> // connected to the internet
                when {
                    activeNetwork.type == ConnectivityManager.TYPE_WIFI -> true
                    activeNetwork.type == ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            else -> false
        }
    }

}