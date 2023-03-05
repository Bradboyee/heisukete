package com.thepparat.heisukete.feature_kanjialive.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "kanji_grade")
data class KanjiByGrade(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val kanji: String,
    val grade: Int,
)