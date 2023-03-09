package com.thepparat.heisukete.feature_kanjialive.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem

@Dao
interface KanjiItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKanjiItem(kanjiItem: List<KanjiItem>)

    @Query("DELETE FROM kanji_item")
    fun clearKanjiItem()

    @Query("SELECT * FROM kanji_item k WHERE k.grade = :grade ")
    fun getKanjiByGrade(grade: Int): List<KanjiItem>

    @Query("SELECT * FROM kanji_item WHERE character IN (:kanjiList) AND grade = :grade")
    fun findKanjiItemsByCharacters(kanjiList: List<String>, grade: Int): List<KanjiItem>
}