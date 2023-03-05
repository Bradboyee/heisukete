package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import java.util.UUID

fun KanjiDetailDto.toKanjiDetail(): KanjiDetail {
    val character = kanji.character
    val english = kanji.meaning.english
    val images = kanji.strokes.images
    val stroke = kanji.strokes.count
    val hiragana = kanji.kunyomi.hiragana
    val katakana = kanji.onyomi.katakana
    val examples = examples.map { it.japanese + it.meaning.english }
    val grade = references.grade
    val id = UUID.randomUUID()
    return KanjiDetail(
        id = id,
        meaning = english,
        character = character,
        images = images,
        stroke = stroke,
        hiragana = hiragana,
        katakana = katakana,
        examples = examples,
        grade = grade
    )
}