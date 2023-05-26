package com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.repository.SpacedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetKanjiQuizItemUseCase @Inject constructor(private val repository : SpacedRepository) {
    operator fun invoke(): Flow<Resource<List<KanjiQuizItem>>> {
        return repository.getKanjiQuizItem()
    }
}