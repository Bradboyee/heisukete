package com.thepparat.heisukete.feature_kanjialive.data.remote.dto


import com.google.gson.annotations.SerializedName

data class KanjiByGradeDto(
    @SerializedName("kanji")
    val kanji: KanjiByGradeItemDto,
    val references: Reference,
) {
    data class Reference(
        val grade: Int,
    )
}
