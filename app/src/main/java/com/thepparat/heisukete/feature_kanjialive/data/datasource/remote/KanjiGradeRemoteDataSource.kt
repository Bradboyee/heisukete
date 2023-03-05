package com.thepparat.heisukete.feature_kanjialive.data.datasource.remote

import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto

interface KanjiGradeRemoteDataSource {
    suspend fun getKanjiGradeFromApi(grade : Int): List<KanjiByGradeDto>
    suspend fun getKanjiDetailFromApi(kanji : String) : KanjiDetailDto
}