package com.example.android2lresson2dz.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.android2lresson2dz.models.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(noteModel: NoteModel)

    @Delete
    fun delete(model: NoteModel)
}