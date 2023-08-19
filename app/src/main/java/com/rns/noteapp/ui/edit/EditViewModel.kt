package com.rns.noteapp.ui.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.data.repository.NoteRepository
import com.rns.noteapp.data.repository.NoteRepositoryImpl
import com.rns.noteapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditViewModel @Inject constructor(private val repository: NoteRepositoryImpl) : ViewModel(),
    EditInteractionListener {
    val date = MutableStateFlow(Date())
    var titleEdit = MutableStateFlow<String?>(null)
    var contentEdit = MutableStateFlow<String?>(null)
    val currentNote = MutableStateFlow<Note?>(null)
    val deleteStatus = MutableStateFlow(Event(false))


    init {
        collectTitle()
        collectContent()
    }

    private fun collectTitle() {
        viewModelScope.launch {
            titleEdit.collect {
                emitNote()
            }
        }
    }

    private fun collectContent() {
        viewModelScope.launch {
            contentEdit.collect {
                emitNote()
            }
        }
    }

    private suspend fun emitNote() {
        currentNote.emit(
            currentNote.value?.apply {
                title = titleEdit.value ?: ""
                content = contentEdit.value ?: ""
            }
        )
    }

    override fun updateNote(note: Note) {
        viewModelScope.launch {
            repository.updateNote(note)
        }
    }

    override fun loadNote(id: Long) {
        viewModelScope.launch {
            repository.getNote(id).collect {
                currentNote.emit(it)
            }
        }

        viewModelScope.launch {
            currentNote.collect { note ->
                titleEdit.emit(note?.title)
                contentEdit.emit(note?.content)
                note?.date?.let { date.emit(it) }
            }
        }
    }

    override fun deleteNote() {
        viewModelScope.launch {
            currentNote.value?.let { repository.deleteNote(it) }
            deleteStatus.emit(Event(true))
        }
    }
}