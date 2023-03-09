package com.thepparat.heisukete.feature_kanjialive.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiItem
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetByGradeUseCase(private val repository: GetKanjiRepository) {
    suspend operator fun invoke(grade: Int): Flow<Resource<List<KanjiItem>>> {
        return repository.getGrade(grade = grade)
    }
}