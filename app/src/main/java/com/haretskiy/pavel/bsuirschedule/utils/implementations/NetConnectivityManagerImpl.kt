package com.haretskiy.pavel.bsuirschedule.utils.implementations

import android.content.Context
import android.net.ConnectivityManager
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.NetConnectivityManager
import javax.inject.Inject


class NetConnectivityManagerImpl @Inject constructor(private val context: Context) : NetConnectivityManager {

    override fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        when {
            activeNetwork != null -> // connected to the internet
                when {
                    activeNetwork.type == ConnectivityManager.TYPE_WIFI -> return true
                    activeNetwork.type == ConnectivityManager.TYPE_MOBILE -> return true
                    else -> return false
                }
            else -> return false
        }
    }

}