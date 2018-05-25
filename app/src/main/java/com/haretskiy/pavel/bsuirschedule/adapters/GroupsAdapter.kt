package com.haretskiy.pavel.bsuirschedule.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haretskiy.pavel.bsuirschedule.R
import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.ui.views.GroupHolder
import com.haretskiy.pavel.bsuirschedule.ui.views.GroupView

class GroupsAdapter(var listOfGroups: List<Group>,
                    private val groupView: GroupView) : RecyclerView.Adapter<GroupHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupHolder(view, groupView)
    }

    override fun getItemCount() = listOfGroups.size

    override fun onBindViewHolder(holder: GroupHolder, position: Int) {
        holder.bindHolder(listOfGroups[position])
    }


}