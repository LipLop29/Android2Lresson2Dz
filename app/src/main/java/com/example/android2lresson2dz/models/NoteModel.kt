package com.example.android2lresson2dz.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "note")
data class NoteModel(
    val title : String,
    val description : String,
    val color: String,
    val time : String,
    val data : String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
