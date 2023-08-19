package com.rns.noteapp.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.data.repository.NoteRepository
import com.rns.noteapp.data.repository.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private val repository: NoteRepositoryImpl) : ViewModel(), AddInteractionListener {
    val date = MutableStateFlow(Date())
    var title = MutableStateFlow<String?>(null)
    var content = MutableStateFlow<String?>(null)
    val currentNote = MutableStateFlow<Note?>(null)

    init {
        collectTitle()
        collectContent()
    }

    private fun collectTitle() {
        viewModelScope.launch {
            title.collect {
                emitNote()
            }
        }
    }

    private fun collectContent() {
        viewModelScope.launch {
            content.collect {
                emitNote()
            }
        }
    }

    private suspend fun emitNote() {
        currentNote.emit(
            Note(
                title = title.value ?: "",
                content = content.value ?: "",
            )
        )
    }

    override fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
            reset()
        }
    }

    private fun reset(){
        title.value = null
        content.value = null
    }
}