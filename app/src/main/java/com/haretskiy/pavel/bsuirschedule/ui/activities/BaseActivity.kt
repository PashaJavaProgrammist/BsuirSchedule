package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.bsuirschedule.R
import dagger.android.AndroidInjection

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(getResLayout())
    }

    override fun onBackPressed() {
        finishAffinity()
    }

    abstract fun getResLayout(): Int
}