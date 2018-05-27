package com.haretskiy.pavel.bsuirschedule.ui.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.ScheduleAdapter
import com.haretskiy.pavel.bsuirschedule.models.ScheduleUnit
import com.haretskiy.pavel.bsuirschedule.ui.activities.ScheduleActivity.TimeState
import kotlinx.android.synthetic.main.fragment_schedule.*

class ScheduleFragment : Fragment() {

    var timeState: TimeState = TimeState.FUTURE

    val adapter: ScheduleAdapter by lazy {
        ScheduleAdapter(emptyList())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_schedule, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListOfSubjects()
        initFabHiding()
    }

    private fun initListOfSubjects() {
        rv_schedule.layoutManager = LinearLayoutManager(activity)
        rv_schedule.adapter = adapter
    }


    fun setSchedule(schedule: List<ScheduleUnit>) {
        adapter.timeState = timeState
        adapter.listOfSchedule = schedule
        adapter.notifyDataSetChanged()
    }

    private fun initFabHiding() {
        rv_schedule.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        val fab = activity?.findViewById<FloatingActionButton>(R.id.fab)
                        if (fab != null)
                            if (dy > 0 && fab.visibility == View.VISIBLE) {
                                fab.hide()
                            } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                                fab.show()
                            }
                    }
                })
    }

}
