package com.haretskiy.pavel.bsuirschedule.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.haretskiy.pavel.bsuirschedule.App
import com.haretskiy.pavel.bsuirschedule.GroupsAdapter
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import com.haretskiy.pavel.bsuirschedule.viewModels.factories.GroupsViewModelFactory
import com.haretskiy.pavel.bsuirschedule.views.GroupView
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : BaseActivity(), GroupView {

    val adapter: GroupsAdapter by lazy {
        GroupsAdapter(emptyList(), this)
    }

    override fun getResLayout() = R.layout.activity_groups

    private lateinit var groupsViewModel: GroupsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        groupsViewModel = ViewModelProviders.of(this, GroupsViewModelFactory(application as App)).get(GroupsViewModel::class.java)

        setSupportActionBar(toolbar_search)

        setRecyclerView()

        getGroupsLiveDataAndSubscribe()
    }

    private fun setRecyclerView() {
        rv_groups.layoutManager = LinearLayoutManager(this)
        rv_groups.adapter = adapter
    }

    private fun getGroupsLiveDataAndSubscribe() {
        groupsViewModel.groupsLiveData.observe(this, Observer {
            if (it != null) {
                adapter.listOfGroups = it
                adapter.notifyDataSetChanged()
            }
        })
        groupsViewModel.loadGroupsList()
    }

    override fun onClickGroup(name: String) {
        //TODO: To change body of created functions use File | Settings | File Templates.
    }
}
