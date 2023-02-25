package com.thepparat.heisukete.feature_kanjialive.data.remote

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KanjiAliveApi {
    @GET("api/public/kanji/all")
    suspend fun getKanjiByGrade(@Query("grade") grade: Int): List<KanjiByGradeDto>

    @GET("api/public/kanji/{kanji}")
    suspend fun getKanjiDetail(@Path("kanji") kanji: String): KanjiDetailDto
}