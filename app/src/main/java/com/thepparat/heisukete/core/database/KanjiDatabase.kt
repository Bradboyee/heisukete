package com.thepparat.heisukete.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiDetailDao
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiGradeDao
import com.thepparat.heisukete.feature_kanjialive.data.dao.KanjiItemDao
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.util.StringListConverter
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemDao
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.local.KanjiQuizItemEntity

@Database(
    version = 1,
    entities = [KanjiItem::class, KanjiDetail::class, KanjiByGrade::class, KanjiQuizItemEntity::class]
)
@TypeConverters(value = [StringListConverter::class])
abstract class KanjiDatabase : RoomDatabase() {
    abstract fun kanjiGradeDao(): KanjiGradeDao
    abstract fun kanjiDetailDao(): KanjiDetailDao
    abstract fun kanjiItemDao(): KanjiItemDao

    abstract fun kanjiQuizItemDao() : KanjiQuizItemDao
}