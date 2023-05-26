package com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource

import com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource.local.KanjiQuizItemLocalDataSource
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemDao
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemEntity
import javax.inject.Inject

class KanjiQuizItemLocalDataSourceImpl @Inject constructor(private val dao: KanjiQuizItemDao) :
    KanjiQuizItemLocalDataSource {
    override fun getKanjiKanjiQuizItem(): List<KanjiQuizItemEntity> {
        return dao.getKanjiQuizItem()
    }

    override fun getKanjiQuizItemByCharacter(kanji: String): Int {
        return dao.getKanjiQuizItemByCharacter(kanji = kanji)
    }

    override suspend fun upsertQuizItem(kanjiQuizItemEntity: KanjiQuizItemEntity) {
        return dao.upsertQuizItem(kanjiQuizItemEntity)
    }

    override suspend fun delete(kanjiQuizItemEntity: KanjiQuizItemEntity) {
        return dao.delete(kanjiQuizItemEntity = kanjiQuizItemEntity)
    }


}