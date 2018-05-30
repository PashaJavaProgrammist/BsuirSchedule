package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
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

    protected fun initSwipeToRefresh(swipe: SwipeRefreshLayout) {
        swipe.setOnRefreshListener({ onSwipeToRefresh() })
        swipe.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
    }

    protected fun swipeAnimFinish(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = false
    }

    abstract fun getResLayout(): Int

    abstract fun onSwipeToRefresh()
}