package com.thepparat.heisukete.space_repeat_feature.data.entity.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "kanji_quiz")
class KanjiQuizItemEntity(
    @PrimaryKey
    val character: String,
    val grade: Int,
    val meaning: String,
    val create_dt: Date,
    val update_dt: Date,
    val do_date : Date,
    val repeat_level : Int,
)
