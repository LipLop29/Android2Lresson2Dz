package com.example.android2lresson2dz.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context) {
        sharedPreference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    var onBoard: Boolean
        set(value) = sharedPreference.edit().putBoolean("key", value).apply()
        get() = sharedPreference.getBoolean("key", false)

    var signIn: Boolean
        set(value) = sharedPreference.edit().putBoolean("key1", value).apply()
        get() = sharedPreference.getBoolean("key1", false)
}