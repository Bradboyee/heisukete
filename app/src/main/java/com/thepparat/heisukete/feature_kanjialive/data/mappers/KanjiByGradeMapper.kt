package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade

fun KanjiByGradeDto.toKanjiByGrade(): KanjiByGrade {
    return KanjiByGrade(kanji = kanji.character)
}