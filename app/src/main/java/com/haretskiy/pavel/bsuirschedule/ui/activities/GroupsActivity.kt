package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v7.appcompat.R.id.search_src_text
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.TextView
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.adapters.GroupsAdapter
import com.haretskiy.pavel.bsuirschedule.ui.dialogs.GroupDefaultDialog
import com.haretskiy.pavel.bsuirschedule.ui.views.GroupView
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import kotlinx.android.synthetic.main.activity_groups.*
import kotlinx.android.synthetic.main.search_toobar.*
import javax.inject.Inject


class GroupsActivity : BaseActivity(), GroupView {

    private val adapter: GroupsAdapter by lazy {
        GroupsAdapter(emptyList(), this)
    }

    @Inject
    lateinit var groupsViewModel: GroupsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_search)

        setRecyclerView()

        getGroupsLiveDataAndSubscribe()

        initSwipeToRefresh()

        initSearchView()
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
                adapter.listOfGroups = it
                adapter.notifyDataSetChanged()
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

    private fun initSearchView() {

        val textView = search_view.findViewById(search_src_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.setHintTextColor(Color.WHITE)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?) = true

            override fun onQueryTextChange(searchText: String?): Boolean {

                if (searchText != null) {
                    groupsViewModel.search(searchText)
                }

                return true
            }
        })
    }


    override fun onClickGroup(name: String) {
        GroupDefaultDialog().show(supportFragmentManager, object : GroupDefaultDialog.DefaultGroupListener {
            override fun onClickSave() {
                groupsViewModel.saveGroupAsDefault(name)
            }

            override fun onClickDismiss() {}

            override fun onDismiss() {
                groupsViewModel.startScheduleActivity(name)
            }
        })
    }
}
