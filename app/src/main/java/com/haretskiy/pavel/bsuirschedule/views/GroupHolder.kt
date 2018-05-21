package com.haretskiy.pavel.bsuirschedule.views

import android.support.v7.widget.RecyclerView
import android.view.View
import com.haretskiy.pavel.bsuirschedule.models.Group
import kotlinx.android.synthetic.main.item_group.view.*

class GroupHolder(private val view: View, private val groupView: GroupView) : RecyclerView.ViewHolder(view) {

    fun bindHolder(group: Group) {
        view.group_number.text = group.name

        view.group_item.setOnClickListener {
            groupView.onClickGroup(group.name)
        }
    }

}