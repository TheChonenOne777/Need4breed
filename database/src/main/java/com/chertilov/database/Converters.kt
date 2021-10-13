package com.chertilov.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromList(value: Set<String>): String = value.joinToString(DATA_DELIMITER)

    @TypeConverter
    fun stringToList(string: String): Set<String> = string.split(DATA_DELIMITER).map { it }.toSet()

    companion object {
        private const val DATA_DELIMITER = "#-#"
    }
}