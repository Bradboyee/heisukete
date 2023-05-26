package com.thepparat.heisukete.space_repeat_feature.data.entity.di.usecase

import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.GetKanjiQuizItemUseCase
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.UpsertKanjiQuizItemUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SpacedUseCaseModule {
    @Provides
    @Singleton
    fun providesGetKanjiQuizItemUseCase(repository: SpacedRepository): GetKanjiQuizItemUseCase {
        return GetKanjiQuizItemUseCase(repository = repository)
    }

    @Provides
    @Singleton
    fun providesUpsertKanjiQuizItemUseCase(repository: SpacedRepository): UpsertKanjiQuizItemUseCase {
        return UpsertKanjiQuizItemUseCase(repository = repository)
    }
}