package com.thepparat.heisukete.feature_kanjialive.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail

@Dao
interface KanjiDetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(kanjiDetail: KanjiDetail)

    @Query("DELETE FROM kanji_detail WHERE character = :kanji ")
    suspend fun delete(kanji: String)

    @Query("SELECT * FROM kanji_detail kd WHERE kd.character = :kanji")
    fun getDetailByKanji(kanji: String): KanjiDetail?
}