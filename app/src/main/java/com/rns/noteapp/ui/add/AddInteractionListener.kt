package com.rns.noteapp.ui.add

import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.ui.base.BaseInteractionListener

interface AddInteractionListener : BaseInteractionListener {
    fun addNote(note: Note)
}