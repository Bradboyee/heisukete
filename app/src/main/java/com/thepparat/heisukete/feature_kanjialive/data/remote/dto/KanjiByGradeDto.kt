package com.thepparat.heisukete.feature_kanjialive.data.remote.dto


import com.google.gson.annotations.SerializedName

data class KanjiByGradeDto(
    @SerializedName("kanji")
    val kanji: Kanji,
    val references: Reference,
) {
    data class Reference(
        val grade: Int,
    )

    data class Kanji(
        val character: String,
        val meaning: Meaning,
        val onyomi: Onyomi,
        val kunyomi: Kunyomi,
    )

    data class Meaning(
        val english: String,
    )

    data class Onyomi(
        val romaji: String,
        val katakana: String,
    )

    data class Kunyomi(
        val romaji: String,
        val hiragana: String,
    )
}
