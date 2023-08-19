package com.rns.noteapp.ui.home

import com.rns.noteapp.R
import com.rns.noteapp.data.entities.Note
import com.rns.noteapp.ui.base.BaseAdapter

class HomeAdapter(items: List<Note>, listener: HomeInteractionListener) :
    BaseAdapter<Note>(_items = items, _listener = listener) {
    override val layoutID: Int
        get() = R.layout.item_note
}