package com.thepparat.heisukete.feature_kanjialive.domain.usecase

import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiByGrade
import com.thepparat.heisukete.feature_kanjialive.domain.repository.GetKanjiRepository
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource

class GetKanjiByGradeUseCase(private val repository: GetKanjiRepository) {
    suspend operator fun invoke(grade: Int): Resource<List<KanjiByGrade>> {
        return repository.getKanjiByGrade(grade = grade)
    }
}