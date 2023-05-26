package com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKanjiQuizItemByCharacterUseCase @Inject constructor(private val spacedRepository: SpacedRepository) {
    operator fun invoke(character: String): Flow<Resource<String>> {
        return spacedRepository.getKanjiQuizItemByCharacter(character = character)
    }
}