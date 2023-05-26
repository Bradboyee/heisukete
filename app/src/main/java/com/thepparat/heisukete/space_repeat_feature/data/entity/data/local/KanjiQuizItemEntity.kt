package com.thepparat.heisukete.space_repeat_feature.data.entity.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "kanji_quiz")
class KanjiQuizItemEntity(
    @PrimaryKey
    val character: String,
    val grade: Int,
    val meaning: String,
)
