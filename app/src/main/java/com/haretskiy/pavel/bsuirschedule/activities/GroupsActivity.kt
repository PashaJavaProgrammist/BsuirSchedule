package com.haretskiy.pavel.bsuirschedule.activities

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.GroupsAdapter
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.views.GroupView
import kotlinx.android.synthetic.main.activity_groups.*
import javax.inject.Inject

class GroupsActivity : BaseActivity(), GroupView {

    private val adapter: GroupsAdapter by lazy {
        GroupsAdapter(emptyList(), this)
    }

    //todo: serchview

    @Inject
    lateinit var groupsViewModel: GroupsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_search)

        setRecyclerView()

        getGroupsLiveDataAndSubscribe()

        initSwipeToRefresh()
    }

    override fun getResLayout() = R.layout.activity_groups

    private fun setRecyclerView() {
        rv_groups.layoutManager = LinearLayoutManager(this)
        rv_groups.adapter = adapter
    }

    private fun getGroupsLiveDataAndSubscribe() {
        progress.visibility = View.VISIBLE
        groupsViewModel.groupsLiveData.observe(this, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    adapter.listOfGroups = it
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@GroupsActivity, getString(R.string.no_groups), Toast.LENGTH_SHORT).show()
                }
            }
            progress.visibility = View.GONE
            swipeAnimFinish()
        })
        groupsViewModel.loadGroupsList(false)
    }

    private fun initSwipeToRefresh() {
        swipe_to_refresh.setOnRefreshListener({ onSwipeToRefresh() })
        swipe_to_refresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light)
    }

    private fun onSwipeToRefresh() {
        groupsViewModel.loadGroupsList(true)
    }

    private fun swipeAnimFinish() {
        swipe_to_refresh.isRefreshing = false
    }

    override fun onClickGroup(name: String) {
        groupsViewModel.startScheduleActivity(name)
    }
}
