package com.rns.noteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rns.noteapp.data.dao.NoteDao
import com.rns.noteapp.data.entities.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun dao(): NoteDao

    companion object {
        const val DB_NAME = "DB_NOTE"
    }
}