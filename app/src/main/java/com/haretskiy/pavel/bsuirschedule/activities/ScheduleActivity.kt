package com.haretskiy.pavel.bsuirschedule.activities

import android.os.Bundle
import com.haretskiy.pavel.bsuirschedule.BUNDLE_KEY_NUMBER_GROUP
import com.haretskiy.pavel.bsuirschedule.R

class ScheduleActivity : BaseActivity() {

    override fun getResLayout() = R.layout.activity_schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val numberOfGroup = intent.getStringExtra(BUNDLE_KEY_NUMBER_GROUP)
    }
}
