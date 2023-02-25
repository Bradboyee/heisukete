package com.thepparat.heisukete.feature_kanjialive.domain.repository

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource

interface GetKanjiRepository {
    suspend fun getKanjiByGrade(grade: Int): Resource<List<KanjiByGrade>>
    suspend fun getKanjiDetailed(kanji : String) : Resource<KanjiDetail>
}