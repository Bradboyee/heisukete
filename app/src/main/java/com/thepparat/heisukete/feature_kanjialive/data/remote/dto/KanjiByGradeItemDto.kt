package com.thepparat.heisukete.feature_kanjialive.data.remote.dto


import com.google.gson.annotations.SerializedName

data class KanjiByGradeItemDto(
    @SerializedName("character")
    val character: String,
)