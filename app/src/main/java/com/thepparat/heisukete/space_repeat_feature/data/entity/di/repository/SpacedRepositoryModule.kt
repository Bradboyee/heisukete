package com.thepparat.heisukete.space_repeat_feature.data.entity.di.repository

import com.thepparat.heisukete.space_repeat_feature.data.entity.data.repository.SpacedRepositoryImpl
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SpacedRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsSpacedRepository(spacedRepositoryImpl: SpacedRepositoryImpl): SpacedRepository
}