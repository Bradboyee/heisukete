package com.thepparat.heisukete.feature_kanjialive.data.datasource.local

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem

interface KanjiItemLocalDataSource {
    suspend fun insertKanjiItem(kanjiItem: List<KanjiItem>)
    fun clearKanjiItem()
    fun findAll(grade: Int): List<KanjiItem>
    fun findKanjiByCharacters(characters: List<String>, grade: Int): List<KanjiItem>
}