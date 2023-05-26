package com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteKanjiQuizItemUseCase @Inject constructor(private val spacedRepository: SpacedRepository) {
    suspend operator fun invoke(kanjiQuizItem: KanjiQuizItem): Flow<Resource<String>> {
        return spacedRepository.deleteQuizItem(kanjiQuizItem = kanjiQuizItem)
    }
}