package com.thepparat.heisukete.feature_kanjialive.data.repository

import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiDetail
import com.thepparat.heisukete.feature_kanjialive.data.remote.KanjiAliveApi
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import javax.inject.Inject

const val ERROR = "An error occurred."

class GetKanjiRepositoryImpl @Inject constructor(private val api: KanjiAliveApi) :
    GetKanjiRepository {
    override suspend fun getKanjiByGrade(grade: Int): Resource<List<KanjiByGrade>> {
        return try {
            val kanjiByGrades = api.getKanjiByGrade(grade = grade).map { it.toKanjiByGrade() }
            (Resource.Success(data = kanjiByGrades))
        } catch (e: Exception) {
            (Resource.Error(message = e.message ?: ERROR))
        }
    }

    override suspend fun getKanjiDetailed(kanji: String): Resource<KanjiDetail> {
        return try {
            val kanjiDetail = api.getKanjiDetail(kanji = kanji).toKanjiDetail()
            Resource.Success(kanjiDetail)
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: ERROR)
        }
    }


}