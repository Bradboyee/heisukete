package com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource.local

import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemEntity
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem

interface KanjiQuizItemLocalDataSource {
    fun getKanjiKanjiQuizItem(): List<KanjiQuizItemEntity>
    fun getKanjiQuizItemByCharacter(kanji: String): Int
    suspend fun upsertQuizItem(kanjiQuizItemEntity: KanjiQuizItemEntity)
    suspend fun delete(kanjiQuizItemEntity: KanjiQuizItemEntity)
}