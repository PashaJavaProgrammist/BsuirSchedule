package com.haretskiy.pavel.bsuirschedule.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.viewModels.GroupsViewModel
import kotlinx.android.synthetic.main.activity_groups.*

class GroupsActivity : BaseActivity() {

    override fun getResLayout() = R.layout.activity_groups

    val groupsViewModel: GroupsViewModel by lazy {
        ViewModelProviders.of(this).get(GroupsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar_search)

        setRecyclerView()

        getGroupsLiveDataAndSubscribe()
    }

    private fun setRecyclerView() {
        rv_groups.layoutManager = LinearLayoutManager(this)
        rv_groups.adapter
    }

    private fun getGroupsLiveDataAndSubscribe() {
        groupsViewModel.groupsLiveData.observe(this, Observer {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        })
        groupsViewModel.loadGroupsList()
    }
}
