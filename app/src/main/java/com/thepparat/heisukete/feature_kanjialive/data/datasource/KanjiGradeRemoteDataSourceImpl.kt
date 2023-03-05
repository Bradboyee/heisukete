package com.thepparat.heisukete.feature_kanjialive.data.datasource

import com.thepparat.heisukete.feature_kanjialive.data.datasource.remote.KanjiGradeRemoteDataSource
import com.thepparat.heisukete.feature_kanjialive.data.remote.KanjiAliveApi
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiByGradeDto
import com.thepparat.heisukete.feature_kanjialive.data.remote.dto.KanjiDetailDto
import javax.inject.Inject

class KanjiGradeRemoteDataSourceImpl @Inject constructor(private val api: KanjiAliveApi) :
    KanjiGradeRemoteDataSource {
    override suspend fun getKanjiGradeFromApi(grade: Int): List<KanjiByGradeDto> {
        return api.getKanjiByGrade(grade = grade)
    }

    override suspend fun getKanjiDetailFromApi(kanji: String): KanjiDetailDto {
        return api.getKanjiDetail(kanji = kanji)
    }
}