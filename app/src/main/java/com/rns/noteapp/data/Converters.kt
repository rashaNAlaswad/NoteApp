package com.rns.noteapp.data

import androidx.room.TypeConverter
import java.util.Date

class Converters {
    @TypeConverter
    fun dateToLong(value: Date) = value.time

    @TypeConverter
    fun longToDate(value: Long) = Date(value)
}