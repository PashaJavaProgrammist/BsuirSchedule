package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.os.Bundle
import android.os.Handler
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.utils.Prefs
import com.haretskiy.pavel.bsuirschedule.utils.Router
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var prefs: Prefs

    @Inject
    lateinit var router: Router

    override fun getResLayout() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity()
        }, 600)
    }

    private fun startActivity() {
        val group = prefs.getDefaultGroup()

        if (group.isNotEmpty()) {
            router.startScheduleActivity(group)
        } else {
            router.startGroupsActivity()
        }
    }
}