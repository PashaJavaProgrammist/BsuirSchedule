package com.haretskiy.pavel.bsuirschedule.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.haretskiy.pavel.bsuirschedule.BUNDLE_KEY_NUMBER_GROUP
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.viewModels.ScheduleViewModel
import javax.inject.Inject

class ScheduleActivity : BaseActivity() {

    override fun getResLayout() = R.layout.activity_schedule

    @Inject
    lateinit var scheduleViewModel: ScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val numberOfGroup = intent.getStringExtra(BUNDLE_KEY_NUMBER_GROUP)


        getGroupsLiveDataAndSubscribe(numberOfGroup, true)
    }

    fun getGroupsLiveDataAndSubscribe(nameOfGroup: String, exam: Boolean) {
        scheduleViewModel.loadSchedule(nameOfGroup, exam)

        scheduleViewModel.scheduleLiveData.observe(this, Observer {
            if (it != null) {
                Toast.makeText(this@ScheduleActivity, "" + it, Toast.LENGTH_LONG).show()
            }
        })

    }
}
