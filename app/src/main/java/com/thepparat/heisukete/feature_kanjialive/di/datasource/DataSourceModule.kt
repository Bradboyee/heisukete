package com.thepparat.heisukete.feature_kanjialive.di.datasource

import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiGradeLocalDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiRemoteDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiDetailLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiDetailLocalDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiGradeLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.remote.KanjiRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindsRemoteDataSource(kanjiRemoteDataSourceImpl: KanjiRemoteDataSourceImpl): KanjiRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsKanjiGradeLocalDataSource(kanjiGradeLocalDataSourceImpl: KanjiGradeLocalDataSourceImpl): KanjiGradeLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsKanjiDetailLocalDataSource(kanjiDetailLocalDataSourceImpl: KanjiDetailLocalDataSourceImpl): KanjiDetailLocalDataSource
}