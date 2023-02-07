package com.example.android2lresson2dz.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context) {
        sharedPreference = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
    }

    var safeBool: Boolean
        get() = sharedPreference.getBoolean("kay", false)
        set(value) = sharedPreference.edit().putBoolean("kay", value).apply()
}