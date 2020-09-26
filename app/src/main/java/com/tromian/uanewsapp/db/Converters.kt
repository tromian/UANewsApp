package com.tromian.uanewsapp.db

import androidx.room.TypeConverter
import com.tromian.uanewsapp.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source) = source.name

    @TypeConverter
    fun toSource(name: String) = Source(name, name)
}