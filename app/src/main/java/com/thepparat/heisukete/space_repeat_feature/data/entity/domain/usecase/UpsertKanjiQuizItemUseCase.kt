package com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UpsertKanjiQuizItemUseCase @Inject constructor(private val repository: SpacedRepository) {
    suspend operator fun invoke(kanjiQuizItem: KanjiQuizItem): Flow<Resource<String>> {
        return repository.upsertQuizItem(kanjiQuizItem)
    }
}