package com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository

import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemEntity
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import kotlinx.coroutines.flow.Flow

interface SpacedRepository {
    fun getKanjiQuizItem() : Flow<Resource<List<KanjiQuizItem>>>
    fun getKanjiQuizItemByCharacter(character : String) : Flow<Resource<String>>
    suspend fun upsertQuizItem(kanjiQuizItem: KanjiQuizItem) : Flow<Resource<String>>
    suspend fun deleteQuizItem(kanjiQuizItem: KanjiQuizItem) : Flow<Resource<String>>
}