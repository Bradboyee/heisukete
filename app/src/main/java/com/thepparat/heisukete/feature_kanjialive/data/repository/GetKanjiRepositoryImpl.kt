package com.thepparat.heisukete.feature_kanjialive.data.repository

import android.util.Log
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiDetailLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiGradeLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiItemLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.remote.KanjiRemoteDataSource
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiDetail
import com.thepparat.heisukete.feature_kanjialive.data.mappers.toKanjiItem
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

const val ERROR = "An error occurred."
const val HTTP_ERROR = "Oops, something went wrong!"

class GetKanjiRepositoryImpl @Inject constructor(
    private val gradeLocal: KanjiGradeLocalDataSource,
    private val detailLocal: KanjiDetailLocalDataSource,
    private val kanjiItemLocal: KanjiItemLocalDataSource,
    private val remote: KanjiRemoteDataSource,
) :
    GetKanjiRepository {
    override suspend fun getKanjiByGrade(grade: Int):
            Flow<Resource<List<KanjiByGrade>>> = flow {
        try {
            //check local
            emit(Resource.Loading())
            val cached = gradeLocal.getKanjiByGrade(grade = grade)
            if (cached.isNotEmpty()) {
                emit(Resource.Success(data = cached))
                return@flow
            }
            //fetch remote
            val kanjiByGrades =
                remote.getKanjiGradeFromApi(grade = grade).map { it.toKanjiByGrade() }
            //cache
            gradeLocal.clearKanjiByGrade()
            gradeLocal.saveKanjiByGrade(kanjiByGrades)
            emit(Resource.Success(data = kanjiByGrades))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ERROR))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = HTTP_ERROR,
                )
            )
        }
    }

    override suspend fun getKanjiDetailed(kanji: String): Flow<Resource<KanjiDetail>> = flow {
        try {
            //check local
            emit(Resource.Loading())
            val detail = detailLocal.getDetailByKanji(kanji = kanji)
            if (detail != null) {
                emit(Resource.Success(data = detail))
                return@flow
            }

            val kanjiDetailFromApi = remote.getKanjiDetailFromApi(kanji).toKanjiDetail()
            detailLocal.delete(kanji = kanji)
            detailLocal.saveDetail(kanjiDetailFromApi)
            emit(Resource.Success(data = kanjiDetailFromApi))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ERROR))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = HTTP_ERROR
                )
            )
        }
    }

    override suspend fun onSearchKanji(
        grade: Int,
        query: String,
    ): Flow<Resource<List<KanjiByGrade>>> = flow {
        Log.d("onSearchKanji", "trigger")
        emit(Resource.Loading())
        val onSearchGradeKanji = gradeLocal.onSearchGradeKanji(grade = grade, query = query)
        if (onSearchGradeKanji.isEmpty()) {
            emit(Resource.Error(message = "Not found any kanji"))
            return@flow
        }
        emit(Resource.Success(data = onSearchGradeKanji))
    }

    override suspend fun getGrade(grade: Int): Flow<Resource<List<KanjiItem>>> = flow {
        try {
            emit(Resource.Loading())
            val findAll = kanjiItemLocal.findAll(grade = grade)
            if (findAll.isNotEmpty()) {
                emit(Resource.Success(data = findAll))
                return@flow
            }
            val kanjiByGradeFromRemote =
                remote.getKanjiByGradeFromRemote(grade).map { it.toKanjiItem(grade = grade) }
            kanjiItemLocal.clearKanjiItem()
            kanjiItemLocal.insertKanjiItem(kanjiByGradeFromRemote)
            emit(Resource.Success(data = kanjiByGradeFromRemote))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ERROR))
        }
    }

    override suspend fun searchKanji(query: String, grade: Int): Flow<Resource<List<KanjiItem>>> =
        flow {
            try {
                emit(Resource.Loading())
                val searchKanjiFromRemote = remote.searchKanjiFromRemote(query)
                if (searchKanjiFromRemote.isEmpty()) {
                    emit(Resource.Error(message = "Not found kanji for $query"))
                    return@flow
                }
                val foundedCharacters = searchKanjiFromRemote.map { it.kanji.character }
                //check it's the same grade
                val findKanjiByCharacters =
                    kanjiItemLocal.findKanjiByCharacters(characters = foundedCharacters,
                        grade = grade)
                if (findKanjiByCharacters.isEmpty()) {
                    emit(Resource.Error("There are no kanji for $query in this grade"))
                    return@flow
                }
                emit(Resource.Success(data = findKanjiByCharacters))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: ERROR))
            } catch (e: HttpException) {
                emit(Resource.Error(message = e.message ?: HTTP_ERROR))
            }
        }

}