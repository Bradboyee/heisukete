package com.thepparat.heisukete.space_repeat_feature.data.entity.data.repository

import android.util.Log
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource.local.KanjiQuizItemLocalDataSource
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.toKanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.toKanjiQuizItemEntity
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

const val ERROR = "An error occurred."

class SpacedRepositoryImpl @Inject constructor(private val localDataSource: KanjiQuizItemLocalDataSource) :
    SpacedRepository {

    override fun getKanjiQuizItem(): Flow<Resource<List<KanjiQuizItem>>> = flow {
        try {
            emit(Resource.Loading())
            val quizItems = localDataSource.getKanjiKanjiQuizItem().map { it.toKanjiQuizItem() }
            if (quizItems.isEmpty()) {
                emit(Resource.Error(message = "Not found kanji quiz."))
                return@flow
            }
            emit(Resource.Success(data = quizItems))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: ERROR))
        }
    }

    override fun getKanjiQuizItemByCharacter(character: String): Flow<Resource<String>> = flow {
        val isExist = localDataSource.getKanjiQuizItemByCharacter(character)
        emit(Resource.Loading())
        if (isExist > 0) {
            emit(Resource.Success(data = "kanji $character are available"))
        } else {
            emit(Resource.Error(message = "kanji $character doesn't exist."))
        }
    }

    override suspend fun upsertQuizItem(kanjiQuizItem: KanjiQuizItem): Flow<Resource<String>> =
        flow {
            try {
                emit(Resource.Loading())
                localDataSource.upsertQuizItem(kanjiQuizItem.toKanjiQuizItemEntity())
                emit(Resource.Success(data = "Success"))
                Log.d("upsertQuizItem", "exe")
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: ERROR))
            }
        }

    override suspend fun deleteQuizItem(kanjiQuizItem: KanjiQuizItem): Flow<Resource<String>> =
        flow {
            emit(Resource.Loading())
            try {
                localDataSource.delete(kanjiQuizItem.toKanjiQuizItemEntity())
                emit(Resource.Success("delete success."))
            } catch (e: Exception) {
                emit(Resource.Error(message = e.message ?: ERROR))
            }
        }

}