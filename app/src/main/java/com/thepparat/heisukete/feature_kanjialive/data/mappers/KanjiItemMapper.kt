package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiItemDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem

fun KanjiItemDto.toKanjiItem(grade: Int): KanjiItem {
    return KanjiItem(
        character = kanji.character,
        stroke = kanji.stroke,
        grade = grade
    )
}