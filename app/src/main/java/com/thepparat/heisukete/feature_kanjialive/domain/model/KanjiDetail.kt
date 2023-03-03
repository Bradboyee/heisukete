package com.thepparat.heisukete.feature_kanjialive.domain.model

data class KanjiDetail(
    val character: String,
    val meaning: String,
    val stroke: Int,
    val images: List<String>?,
    val hiragana: String,
    val katakana: String,
    val examples: List<String>?,
    val grade : Int
)