package com.rns.noteapp.data.repository

import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.data.NoteDatabase
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    val getAllNotes: Flow<List<Note>>

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    fun getNote(id: Long): Flow<Note>

    fun search(query: String): Flow<List<Note>>
}