package com.rns.noteapp.ui.edit

import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.ui.base.BaseInteractionListener

interface EditInteractionListener : BaseInteractionListener {
    fun updateNote(note: Note)
    fun loadNote(id: Long)
    fun deleteNote()
}