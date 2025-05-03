package ru.vopros.foodies.data.room.converters

import androidx.room.TypeConverter

object ListTypeConverter {

    @TypeConverter
    fun fromList(value: List<Int>): String {
        return value.joinToString()
    }

    @TypeConverter
    fun toList(value: String): List<Int> {
        return value.split(", ").map { it.toInt() }
    }

}