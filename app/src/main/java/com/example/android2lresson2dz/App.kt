package com.example.android2lresson2dz

import android.app.Application
import androidx.room.Room
import com.example.android2lresson2dz.data.db.AppDatabase
import com.example.android2lresson2dz.utils.PreferenceHelper

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        initPref()
        getInstance()
    }

    private fun initPref() {
        PreferenceHelper.unit(this)
    }

    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}