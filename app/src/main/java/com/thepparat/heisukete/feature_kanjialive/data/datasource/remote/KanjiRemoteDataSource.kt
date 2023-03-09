package com.thepparat.heisukete.feature_kanjialive.data.datasource.remote

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiItemDto

interface KanjiRemoteDataSource {
    suspend fun getKanjiGradeFromApi(grade: Int): List<KanjiByGradeDto>
    suspend fun getKanjiDetailFromApi(kanji: String): KanjiDetailDto
    suspend fun searchKanjiFromRemote(query: String): List<KanjiItemDto>
    suspend fun getKanjiByGradeFromRemote(grade: Int): List<KanjiItemDto>
}