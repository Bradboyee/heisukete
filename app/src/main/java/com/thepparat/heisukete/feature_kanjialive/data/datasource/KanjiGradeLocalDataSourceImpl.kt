package com.thepparat.heisukete.feature_kanjialive.data.datasource

import com.thepparat.heisukete.feature_kanjialive.data.database.KanjiGradeDao
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiGradeLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class KanjiGradeLocalDataSourceImpl @Inject constructor(private val dao: KanjiGradeDao) :
    KanjiGradeLocalDataSource {
    override suspend fun saveKanjiByGrade(kanjiByGrade: List<KanjiByGrade>) {
        dao.insertKanjiList(kanjiByGrade)
    }

    override fun getKanjiByGrade(grade: Int): List<KanjiByGrade> {
        return dao.getKanjiByGrade(grade = grade)
    }

    override fun clearKanjiByGrade() {
        return dao.clearKanjiGrade()
    }
}