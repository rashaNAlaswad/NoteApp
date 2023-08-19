package com.rns.noteapp.data.repository

import com.rns.noteapp.data.dao.NoteDao
import com.rns.noteapp.data.entities.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao: NoteDao) : NoteRepository {
    override val getAllNotes = dao.getAllNotes()

    override suspend fun insertNote(note: Note) {
        dao.insert(note)
    }

    override suspend fun updateNote(note: Note) {
        dao.update(note)
    }

    override suspend fun deleteNote(note: Note) {
        dao.delete(note)
    }

    override fun getNote(id: Long): Flow<Note> = dao.getNote(id)

    override fun search(query: String): Flow<List<Note>> = dao.search(query)
}