package com.thepparat.heisukete.feature_kanjialive.data.datasource

import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiDetailDao
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiDetailLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import javax.inject.Inject

class KanjiDetailLocalDataSourceImpl @Inject constructor(
    private val dao: KanjiDetailDao,
) : KanjiDetailLocalDataSource {
    override suspend fun getDetailByKanji(kanji: String): KanjiDetail? {
        return dao.getDetailByKanji(kanji)
    }

    override suspend fun delete(kanji: String) {
        return dao.delete(kanji)
    }

    override suspend fun saveDetail(kanjiDetail: KanjiDetail) {
        return dao.insert(kanjiDetail = kanjiDetail)
    }
}