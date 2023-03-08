package com.thepparat.heisukete.feature_kanjialive.di.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiByGradeUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiDetailUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.SearchKanjiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetKanjiUseCase(repository: GetKanjiRepository): GetKanjiByGradeUseCase {
        return GetKanjiByGradeUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideGetKanjiDetailUseCase(repository: GetKanjiRepository): GetKanjiDetailUseCase {
        return GetKanjiDetailUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun provideOnSearchKanjiUseCase(repository: GetKanjiRepository): SearchKanjiUseCase {
        return SearchKanjiUseCase(repository = repository)
    }
}