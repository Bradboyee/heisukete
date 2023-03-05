package com.thepparat.heisukete.feature_kanjialive.di.datasource

import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiGradeLocalDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.KanjiGradeRemoteDataSourceImpl
import com.thepparat.heisukete.feature_kanjialive.data.datasource.local.KanjiGradeLocalDataSource
import com.thepparat.heisukete.feature_kanjialive.data.datasource.remote.KanjiGradeRemoteDataSource
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
    abstract fun bindsRemoteDataSource(kanjiGradeRemoteDataSourceImpl: KanjiGradeRemoteDataSourceImpl): KanjiGradeRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindsLocalDataSource(kanjiGradeLocalDataSourceImpl: KanjiGradeLocalDataSourceImpl): KanjiGradeLocalDataSource
}