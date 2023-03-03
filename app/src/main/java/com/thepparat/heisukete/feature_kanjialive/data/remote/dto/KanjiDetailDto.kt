package com.thepparat.heisukete.feature_kanjialive.data.remote.dto

data class KanjiDetailDto(
    val kanji: Kanji,
    val examples: List<Example>,
    val references : Reference
)

data class Kanji(
    val character: String,
    val meaning: Meaning,
    val strokes: Strokes,
    val onyomi: Onyomi,
    val kunyomi: Kunyomi,
)


data class Meaning(
    val english: String,
)

data class Strokes(
    val count: Int,
    val images: List<String>,
)

data class Onyomi(
    val romaji: String,
    val katakana: String,
)

data class Kunyomi(
    val romaji: String,
    val hiragana: String,
)

data class Example(
    val japanese: String,
    val meaning: Meaning,
)

data class Reference(
    val grade : Int
)
