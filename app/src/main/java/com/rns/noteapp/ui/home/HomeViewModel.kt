package com.rns.noteapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.data.repository.NoteRepositoryImpl
import com.rns.noteapp.utils.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: NoteRepositoryImpl) : ViewModel(),
    HomeInteractionListener {
    private var _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    var searchQuery = MutableStateFlow<String?>(null)

    private var _navigateToEditNote = MutableStateFlow<Event<Long>?>(null)
    val navigateToEditNote get() = _navigateToEditNote.asStateFlow()

        init {
        loadNote()
        search()
    }

    private fun loadNote() {
        viewModelScope.launch {
            repository.getAllNotes.collect {
                _notes.emit(it)
            }
        }
    }

    override fun onItemClicked(id: Long) {
        viewModelScope.launch {
            _navigateToEditNote.emit(Event(id))
        }
    }

    private fun search() {
        viewModelScope.launch {
            searchQuery.debounce(1000).collect(::executeSearch)
        }
    }


    private fun executeSearch(query: String?) {
        when (query.isNullOrEmpty()) {
            true -> loadNote()
            else -> {
                viewModelScope.launch {
                    repository.search(query).collect {
                        _notes.emit(it)
                    }
                }
            }
        }
    }

}