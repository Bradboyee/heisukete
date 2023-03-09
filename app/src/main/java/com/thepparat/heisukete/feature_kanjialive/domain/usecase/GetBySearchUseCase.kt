package com.thepparat.heisukete.feature_kanjialive.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetBySearchUseCase(private val repository: GetKanjiRepository) {
    suspend operator fun invoke(query: String, grade: Int): Flow<Resource<List<KanjiItem>>> {
        if (query.isEmpty()) {
            return repository.getGrade(grade = grade)
        }
        return repository.searchKanji(query = query, grade)
    }
}