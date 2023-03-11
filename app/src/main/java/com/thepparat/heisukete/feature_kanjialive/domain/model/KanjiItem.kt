package com.thepparat.heisukete.feature_kanjialive.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kanji_item")
data class KanjiItem(
    @PrimaryKey(autoGenerate = false)
    val character: String,
    val stroke: Int,
    val grade: Int,
)