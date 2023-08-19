package com.rns.noteapp.ui.home

import com.rns.noteapp.ui.base.BaseInteractionListener

interface HomeInteractionListener: BaseInteractionListener {
    fun onItemClicked(id: Long)
}