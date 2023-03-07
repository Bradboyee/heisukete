package com.thepparat.heisukete.feature_kanjialive.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetKanjiDetailUseCase(private val repository: GetKanjiRepository) {
    suspend operator fun invoke(kanji : String): Flow<Resource<KanjiDetail>> {
        return repository.getKanjiDetailed(kanji)
    }
}