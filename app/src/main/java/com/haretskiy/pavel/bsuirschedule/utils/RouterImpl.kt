package com.haretskiy.pavel.bsuirschedule.utils

import android.content.Context
import android.content.Intent
import com.haretskiy.pavel.bsuirschedule.BUNDLE_KEY_NUMBER_GROUP
import com.haretskiy.pavel.bsuirschedule.ui.activities.GroupsActivity
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity
import javax.inject.Inject

class RouterImpl @Inject constructor(private val context: Context) : Router {

    override fun startScheduleActivity(numberOfGroup: String) {
        val intent = Intent(context, ScheduleActivity::class.java)
        intent.putExtra(BUNDLE_KEY_NUMBER_GROUP, numberOfGroup)
        context.startActivity(intent)
    }

    override fun startGroupsActivity() {
        val intent = Intent(context, GroupsActivity::class.java)
        context.startActivity(intent)
    }
}