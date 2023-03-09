package com.thepparat.heisukete.feature_kanjialive.presentation

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem

data class KanjiByGradeState(
    val kanji: List<KanjiItem>? = null,
    val loading: Boolean = true,
    val error: String? = null,
)