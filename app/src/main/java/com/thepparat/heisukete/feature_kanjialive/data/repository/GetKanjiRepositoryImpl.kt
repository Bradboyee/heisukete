package com.thepparat.heisukete.feature_kanjialive.data.repository

import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiGradeLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.remote.KanjiGradeRemoteDataSource
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

const val ERROR = "An error occurred."

class GetKanjiRepositoryImpl @Inject constructor(
    private val local: KanjiGradeLocalDataSource,
    private val remote: KanjiGradeRemoteDataSource,
) :
    GetKanjiRepository {
    override suspend fun getKanjiByGrade(grade: Int):
            Flow<Resource<List<KanjiByGrade>>> = flow {
        try {
            //check local
            emit(Resource.Loading())
            val cached = local.getKanjiByGrade(grade = grade)
            if (cached.isNotEmpty()) {
                emit(Resource.Success(data = cached))
            }
            //fetch remote
            val kanjiByGrades =
                remote.getKanjiGradeFromApi(grade = grade).map { it.toKanjiByGrade() }
            //cache
            local.clearKanjiByGrade()
            local.saveKanjiByGrade(kanjiByGrades)
            emit(Resource.Success(data = kanjiByGrades))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ERROR))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                )
            )
        }
    }

    override suspend fun getKanjiDetailed(kanji: String): Resource<KanjiDetail> {
        return try {
            val kanjiDetail = remote.getKanjiDetailFromApi(kanji = kanji).toKanjiDetail()
            Resource.Success(kanjiDetail)
        } catch (e: Exception) {
            return Resource.Error(message = e.message ?: ERROR)
        }
    }

}