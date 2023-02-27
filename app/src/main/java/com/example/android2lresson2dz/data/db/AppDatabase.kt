package com.example.android2lresson2dz.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android2lresson2dz.data.db.daos.NoteDao
import com.example.android2lresson2dz.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}