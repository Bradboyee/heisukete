package com.thepparat.heisukete.feature_kanjialive.domain.repository

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface GetKanjiRepository {
    suspend fun getKanjiByGrade(grade: Int): Flow<Resource<List<KanjiByGrade>>>
    suspend fun getKanjiDetailed(kanji: String): Flow<Resource<KanjiDetail>>
    suspend fun onSearchKanji(grade: Int, query: String): Flow<Resource<List<KanjiByGrade>>>
}