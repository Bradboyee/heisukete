package com.thepparat.heisukete.space_repeat_feature.data.entity.di.datasource

import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiItemLocalDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiItemLocalDataSource
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource.KanjiQuizItemLocalDataSourceImpl
import com.thepparat.heisukete.space_repeat_feature.data.entity.data.datasource.local.KanjiQuizItemLocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpacedDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsKanjiQuizItemLocalDataSourceImpl(kanjiQuizItemLocalDataSourceImpl: KanjiQuizItemLocalDataSourceImpl): KanjiQuizItemLocalDataSource

}