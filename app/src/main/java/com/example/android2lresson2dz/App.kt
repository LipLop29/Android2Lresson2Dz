package com.example.android2lresson2dz

import android.app.Application
import com.example.android2lresson2dz.utils.PreferenceHelper

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref() {
        PreferenceHelper.unit(this)
    }
}