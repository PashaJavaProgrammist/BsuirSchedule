package com.haretskiy.pavel.bsuirschedule.utils

import com.haretskiy.pavel.bsuirschedule.models.Group

interface GroupStore {

    fun saveList(listOfGroups: List<Group>)
    fun clearList()
    fun getList(): List<Group>
    fun getListSize(): Int
    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean
}