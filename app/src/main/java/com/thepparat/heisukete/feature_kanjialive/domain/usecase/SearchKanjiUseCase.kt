package com.thepparat.heisukete.feature_kanjialive.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchKanjiUseCase(private val repository: GetKanjiRepository) {
    suspend operator fun invoke(grade: Int, query: String): Flow<Resource<List<KanjiByGrade>>> {
        if (query.isBlank()) {
            return flow { }
        }
        return repository.onSearchKanji(grade = grade, query = query)
    }
}