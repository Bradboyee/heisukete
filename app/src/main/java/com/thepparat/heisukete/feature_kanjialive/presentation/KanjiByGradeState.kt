package com.thepparat.heisukete.feature_kanjialive.presentation

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade

data class KanjiByGradeState(
    val kanji: List<KanjiByGrade>? = null,
    val loading: Boolean = true,
    val error: String? = null,
)