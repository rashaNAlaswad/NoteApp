package com.rns.noteapp.utils

import android.annotation.SuppressLint
import android.view.View
import com.rns.noteapp.ui.base.BaseAdapter
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rns.noteapp.R
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
@BindingAdapter(value = ["app:date"])
fun setData(view: TextView, date: Date?) {
    date?.let { view.text = SimpleDateFormat("MMM dd, yyyy hh:mm a").format(it) }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView?, items: List<T>?) {
    (view?.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
}

@BindingAdapter(value = ["app:visibilityStatus"])
fun <T> setViewVisibility(view: View, items: List<T>?) {
    if (items.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
