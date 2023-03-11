package com.thepparat.heisukete.space_repeat_feature.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class KanjiQuizItem(
    @PrimaryKey
    val character: String,
    val grade: Int,
    val meaning: String,
)
