package com.thepparat.heisukete.feature_kanjialive.data.remote.dto

data class KanjiItemDto(
    val kanji: Kanji,
) {
    data class Kanji(
        val character: String,
        val stroke: Int,
    )
}