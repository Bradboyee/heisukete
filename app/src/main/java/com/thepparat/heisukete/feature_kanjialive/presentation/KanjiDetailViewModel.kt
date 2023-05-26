package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.R
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetByGradeUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiDetailUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.UpsertKanjiQuizItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanjiDetailViewModel @Inject constructor(
    private val getKanjiDetailUseCase: GetKanjiDetailUseCase,
    private val getByGradeUseCase: GetByGradeUseCase,
    private val upsertKanjiQuizItemUseCase: UpsertKanjiQuizItemUseCase
) :
    ViewModel() {

    private var _state = mutableStateOf(KanjiDetailState(loading = true))
    val state: State<KanjiDetailState> = _state


    fun getKanjiDetail(kanji: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiDetailUseCase.invoke(kanji).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = result.message,
                            loading = false
                        )
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            detail = result.data,
                            loading = false,
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}

data class KanjiDetailState(
    val detail: KanjiDetail? = null,
    val error: String? = null,
    val loading: Boolean,
)