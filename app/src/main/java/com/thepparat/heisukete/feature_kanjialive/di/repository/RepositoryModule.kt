package com.thepparat.heisukete.feature_kanjialive.di.repository

import com.thepparat.heisukete.feature_kanjialive.data.repository.GetKanjiRepositoryImpl
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsGetKanjiByGradeRepository(getKanjiByGradeRepositoryImpl: GetKanjiRepositoryImpl): GetKanjiRepository
}