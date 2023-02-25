package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail

fun KanjiDetailDto.toKanjiDetail(): KanjiDetail {
    val character = kanji.character
    val english = kanji.meaning.english
    return KanjiDetail(meaning = english, character = character)
}