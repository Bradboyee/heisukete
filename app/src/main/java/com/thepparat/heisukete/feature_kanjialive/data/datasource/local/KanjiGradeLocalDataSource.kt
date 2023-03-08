package com.thepparat.heisukete.feature_kanjialive.data.datasource.local

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade

interface KanjiGradeLocalDataSource {
    suspend fun saveKanjiByGrade(kanjiByGrade: List<KanjiByGrade>)
    fun getKanjiByGrade(grade: Int): List<KanjiByGrade>
    fun clearKanjiByGrade()
    fun onSearchGradeKanji(grade: Int, query: String): List<KanjiByGrade>
}