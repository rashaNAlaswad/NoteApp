package com.rns.noteapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String = "",
    var content: String = "",
    var date: Date = Date()
)
