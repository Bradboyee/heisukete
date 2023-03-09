package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import java.util.*

fun KanjiByGradeDto.toKanjiByGrade(): KanjiByGrade {
    return KanjiByGrade(
        id = UUID.randomUUID(),
        kanji = kanji.character,
        grade = references.grade,
        katakana = kanji.onyomi.katakana,
        katakanaRomaji = kanji.onyomi.romaji,
        hiragana = kanji.kunyomi.hiragana,
        hiraganaRomaji = kanji.kunyomi.romaji,
        meaning = kanji.meaning.english
    )
}