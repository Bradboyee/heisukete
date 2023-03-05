package com.thepparat.heisukete.feature_kanjialive.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.util.StringListConverter
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail

@Database(
    version = 2,
    entities = [KanjiByGrade::class, KanjiDetail::class])
@TypeConverters(value = [StringListConverter::class])
abstract class KanjiDatabase : RoomDatabase() {
    abstract fun kanjiGradeDao(): KanjiGradeDao
    abstract fun kanjiDetailDao() : KanjiDetailDao
}