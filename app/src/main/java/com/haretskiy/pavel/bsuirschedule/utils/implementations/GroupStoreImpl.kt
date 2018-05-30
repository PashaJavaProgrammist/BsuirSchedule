package com.haretskiy.pavel.bsuirschedule.utils.implementations

import com.haretskiy.pavel.bsuirschedule.models.Group
import com.haretskiy.pavel.bsuirschedule.utils.interfaces.GroupStore
import javax.inject.Inject

class GroupStoreImpl @Inject constructor() : GroupStore {

    private var listOfGroups: List<Group> = emptyList()

    override fun getListSize() = listOfGroups.size

    override fun saveList(listOfGroups: List<Group>) {
        this.listOfGroups = listOfGroups
    }

    override fun clearList() {
        listOfGroups = emptyList()
    }

    override fun getList() = listOfGroups

    override fun isEmpty() = listOfGroups.isEmpty()

    override fun isNotEmpty() = listOfGroups.isNotEmpty()
}