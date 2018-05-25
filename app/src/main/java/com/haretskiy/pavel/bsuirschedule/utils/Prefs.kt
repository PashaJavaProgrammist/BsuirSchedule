package com.haretskiy.pavel.bsuirschedule.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.haretskiy.pavel.bsuirschedule.BUNDLE_DEFAULT_GROUP
import com.haretskiy.pavel.bsuirschedule.EMPTY_STRING


class Prefs(context: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun saveDefaultGroup(name: String) {
        save(BUNDLE_DEFAULT_GROUP, name)
    }

    fun getDefaultGroup() = getString(BUNDLE_DEFAULT_GROUP, EMPTY_STRING)

    //Prefs methods
    private fun save(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    private fun save(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun save(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    private fun save(key: String, value: Float) {
        editor.putFloat(key, value).apply()
    }

    private fun save(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    private fun save(key: String, value: Set<String>) {
        editor.putStringSet(key, value).apply()
    }

    private fun getBoolean(key: String, defValue: Boolean) = preferences.getBoolean(key, defValue)


    private fun getString(key: String, defValue: String) = preferences.getString(key, defValue)


    private fun getInt(key: String, defValue: Int) = preferences.getInt(key, defValue)


    private fun getFloat(key: String, defValue: Float) = preferences.getFloat(key, defValue)


    private fun getLong(key: String, defValue: Long) = preferences.getLong(key, defValue)


    private fun getStringSet(key: String, defValue: Set<String>) = preferences.getStringSet(key, defValue)


    private fun getAll(): Map<String, *> = preferences.all

    private fun remove(key: String) {
        editor.remove(key).apply()
    }

}
