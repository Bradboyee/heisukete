package com.thepparat.heisukete.feature_kanjialive.data.remote.dto

data class KanjiDetailDto(
    val kanji: Kanji,
)

data class Kanji(
    val character: String,
    val meaning: Meaning,
)

data class Meaning(
    val english: String,
)
