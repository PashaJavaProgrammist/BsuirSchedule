package com.haretskiy.pavel.bsuirschedule.ui.activities

import android.arch.lifecycle.Observer
import android.graphics.Color
import android.os.Bundle
import android.support.v7.appcompat.R.id.search_src_text
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

        initViews()

        setSupportActionBar(toolbar_search)

        initObservers()

        loadGroups()
    }

    override fun getResLayout() = R.layout.activity_groups

    override fun onSwipeToRefresh() {
        groupsViewModel.loadGroupsList(true)
    }

    override fun onClickGroup(name: String) {
        GroupDefaultDialog().show(supportFragmentManager, object : GroupDefaultDialog.DefaultGroupListener {
            override fun onClickSave(): Unit = groupsViewModel.saveGroupAsDefault(name)

            override fun onClickDismiss() {}

            override fun onDismiss(): Unit = groupsViewModel.startScheduleActivity(name)

        })
    }

    private fun initViews() {

        setRecyclerView()

        initSwipeToRefresh(swipe_to_refresh)

        initSearchView()

        initFabHiding()

        initFab()
    }

    private fun setProgressVisibility(visible: Boolean) {
        progress.visibility = when (visible) {
            true -> View.VISIBLE
            false -> View.GONE
        }
    }

    private fun setRecyclerView() {
        rv_groups.layoutManager = LinearLayoutManager(this)
        rv_groups.adapter = adapter
    }

    private fun loadGroups() {
        if (!groupsViewModel.getGroupsLoadingInProgress()) {
            groupsViewModel.loadGroupsList(false)
        }
    }

    private fun initObservers() {
        groupsViewModel.getGroupsGroupsLiveData().observe(this, Observer {
            if (it != null) {
                adapter.listOfGroups = it
                adapter.notifyDataSetChanged()
            }
        })
        groupsViewModel.getGroupsProgressLiveData().observe(this, Observer {
            setProgressVisibility(it ?: false)
        })
        groupsViewModel.getGroupsSwipeLiveData().observe(this, Observer {
            setSwipeAnim(it ?: false)
        })
        groupsViewModel.getGroupsConnectionLiveData().observe(this, Observer {
            setNoInternetVisible(it ?: false)
        })
    }

    private fun setSwipeAnim(visible: Boolean) {
        when (visible) {
            true -> swipeAnimStart(swipe_to_refresh)
            false -> swipeAnimFinish(swipe_to_refresh)
        }
    }

    private fun setNoInternetVisible(visible: Boolean) {
        when (visible) {
            true -> no_internet_image.visibility = View.GONE
            false -> no_internet_image.visibility = View.VISIBLE
        }
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

    private fun initFab() {
        fab_groups.visibility = if (groupsViewModel.isDefaultGroupExist()) View.VISIBLE else View.GONE
        fab_groups.setOnClickListener {
            groupsViewModel.startDefaultScheduleActivity()
        }
    }

    private fun initFabHiding() {
        rv_groups.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        if (fab_groups != null)
                            if (dy > 0 && fab_groups.visibility == View.VISIBLE) {
                                fab_groups.hide()
                            } else if (dy < 0 && fab_groups.visibility != View.VISIBLE && groupsViewModel.isDefaultGroupExist()) {
                                fab_groups.show()
                            }
                    }
                })
    }
}
