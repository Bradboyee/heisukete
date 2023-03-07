package com.thepparat.heisukete.feature_kanjialive.data.datasource.local

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail

interface KanjiDetailLocalDataSource {
    suspend fun getDetailByKanji(kanji: String): KanjiDetail?
    suspend fun delete(kanji: String)
    suspend fun saveDetail(kanjiDetail: KanjiDetail)
}