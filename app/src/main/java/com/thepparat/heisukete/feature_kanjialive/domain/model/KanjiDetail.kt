package com.thepparat.heisukete.feature_kanjialive.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "kanji_detail")
data class KanjiDetail(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val character: String,
    val meaning: String,
    val stroke: Int,
    val images: List<String>?,
    val hiragana: String,
    val katakana: String,
    val examples: List<String>?,
    val grade: Int,
)