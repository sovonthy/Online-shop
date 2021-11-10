package com.group6.onlineshop.data

import android.content.Context
import android.content.SharedPreferences
import com.group6.onlineshop.utils.Constant.Login.ACCESS_TOKEN

class SharedPreferenceHelper {

    companion object {

        private var prefs: SharedPreferences? = null
        private var prefsHelper: SharedPreferenceHelper? = null

        @Synchronized
        fun getInstance(context: Context): SharedPreferenceHelper {
            if (prefsHelper == null) {
                prefsHelper = SharedPreferenceHelper()
                prefs = context.getSharedPreferences("onlineShop", 0)
            }
            return prefsHelper!!
        }
    }

    fun setAccessToken(value: String) {
        val editor = prefs!!.edit()
        editor.putString(ACCESS_TOKEN, value)
        editor.apply()
    }

    fun getAccessToken() = prefs!!.getString(ACCESS_TOKEN, "") ?: ""
}