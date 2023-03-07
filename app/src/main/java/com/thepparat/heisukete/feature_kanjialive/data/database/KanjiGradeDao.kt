package com.thepparat.heisukete.feature_kanjialive.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade

@Dao
interface KanjiGradeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKanjiList(kanjiList: List<KanjiByGrade>)

    @Query("DELETE FROM kanji_grade")
    fun clearKanjiGrade()

    @Query("SELECT * FROM kanji_grade kanji WHERE kanji.grade = :grade ")
    fun getKanjiByGrade(grade: Int): List<KanjiByGrade>

    @Query("SELECT * FROM kanji_grade kanji WHERE kanji.grade = :grade AND  kanji.meaning LIKE '%' || :query || '%'")
    fun onSearchKanji(grade: Int, query: String): List<KanjiByGrade>
}