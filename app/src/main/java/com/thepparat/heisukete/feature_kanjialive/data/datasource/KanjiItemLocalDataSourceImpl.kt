package com.thepparat.heisukete.feature_kanjialive.data.datasource

import com.thepparat.heisukete.feature_kanjialive.data.database.KanjiItemDao
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiItemLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem
import javax.inject.Inject

class KanjiItemLocalDataSourceImpl @Inject constructor(private val dao: KanjiItemDao) :
    KanjiItemLocalDataSource {
    override suspend fun insertKanjiItem(kanjiItem: List<KanjiItem>) {
        return dao.insertKanjiItem(kanjiItem)
    }

    override fun clearKanjiItem() {
        return dao.clearKanjiItem()
    }

    override fun findAll(grade: Int): List<KanjiItem> {
        return dao.getKanjiByGrade(grade = grade)
    }

    override fun findKanjiByCharacters(characters: List<String>, grade: Int): List<KanjiItem> {
        return dao.findKanjiItemsByCharacters(characters, grade)
    }

}