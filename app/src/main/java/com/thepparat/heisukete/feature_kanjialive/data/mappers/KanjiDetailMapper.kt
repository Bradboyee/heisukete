package com.thepparat.heisukete.feature_kanjialive.data.mappers

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import java.util.*

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
    val radicalCharacter = radical.character
    val radicalStroke = radical.strokes
    val radicalImage = radical.image
    val position = radical.position.hiragana
    val positionRomaji = radical.position.romaji
    val positionIcon = radical.position.icon
    val radicalHiragana = radical.name.hiragana
    val radicalRomaji = radical.name.romaji
    return KanjiDetail(
        id = id,
        meaning = english,
        character = character,
        images = images,
        stroke = stroke,
        hiragana = hiragana,
        katakana = katakana,
        examples = examples,
        grade = grade,
        radical = radicalCharacter,
        radicalStroke = radicalStroke,
        radicalImage = radicalImage,
        position = position,
        positionRomaji = positionRomaji,
        positionIcon = positionIcon,
        radicalHiragana = radicalHiragana,
        radicalRomaji = radicalRomaji
    )
}