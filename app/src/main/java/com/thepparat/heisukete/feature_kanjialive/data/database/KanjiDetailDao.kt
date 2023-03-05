package com.thepparat.heisukete.feature_kanjialive.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail

@Dao
interface KanjiDetailDao {
    @Insert
    suspend fun insert(kanjiDetail: KanjiDetail)

    @Delete
    suspend fun delete(kanjiDetail: KanjiDetail)
}