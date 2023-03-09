package com.thepparat.heisukete.feature_kanjialive.data.remote

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiItemDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KanjiAliveApi {
    @GET("api/public/kanji/all")
    suspend fun getKanjiByGrade(@Query("grade") grade: Int): List<KanjiByGradeDto>

    @GET("api/public/kanji/{kanji}")
    suspend fun getKanjiDetail(@Path("kanji") kanji: String): KanjiDetailDto

    @GET("api/public/search/advanced")
    suspend fun getGrade(@Query("grade") grade: Int): List<KanjiItemDto>

    @GET("api/public/search/{query}")
    suspend fun searchKanji(@Path("query") query: String): List<KanjiItemDto>
}