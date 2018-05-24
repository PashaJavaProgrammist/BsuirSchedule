package com.haretskiy.pavel.bsuirschedule.utils

import com.haretskiy.pavel.bsuirschedule.models.Group

class GroupStoreImpl : GroupStore {

    private var listOfGroups: List<Group> = emptyList()

    override fun getListSize() = listOfGroups.size

    override fun saveList(listOfGroups: List<Group>) {
        this.listOfGroups = listOfGroups
    }

    override fun clearList() {
        listOfGroups = emptyList()
    }

    override fun getList() = listOfGroups
}