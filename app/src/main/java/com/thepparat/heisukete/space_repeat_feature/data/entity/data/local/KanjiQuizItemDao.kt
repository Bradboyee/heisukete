package com.thepparat.heisukete.space_repeat_feature.data.entity.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface KanjiQuizItemDao {
    @Query("SELECT * FROM kanji_quiz")
    fun getKanjiQuizItem(): List<KanjiQuizItemEntity>

    @Query("SELECT COUNT(1) FROM kanji_quiz WHERE character = :kanji")
    fun getKanjiQuizItemByCharacter(kanji: String): Int

    @Delete
    suspend fun delete(kanjiQuizItemEntity: KanjiQuizItemEntity)

    @Upsert
    suspend fun upsertQuizItem(kanjiQuizItemEntity: KanjiQuizItemEntity)
}