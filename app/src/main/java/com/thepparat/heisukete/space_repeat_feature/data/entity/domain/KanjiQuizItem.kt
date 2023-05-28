package com.thepparat.heisukete.space_repeat_feature.data.entity.domain

import java.util.Date

data class KanjiQuizItem(
    val character: String,
    val grade: Int,
    val meaning: String,
    val create_dt : Date,
    val update_dt : Date,
    val do_date : Date,
    val repeat_level : Int
)