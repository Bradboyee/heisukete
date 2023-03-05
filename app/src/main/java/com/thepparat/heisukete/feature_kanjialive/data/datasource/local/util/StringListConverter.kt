package com.thepparat.heisukete.feature_kanjialive.data.datasource.local.util

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it }
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(",")
    }
}