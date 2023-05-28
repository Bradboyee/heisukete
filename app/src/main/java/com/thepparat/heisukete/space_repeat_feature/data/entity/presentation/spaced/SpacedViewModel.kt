package com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.spaced

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.GetKanjiQuizItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpacedViewModel @Inject constructor(
    private val getKanjiQuizItemUseCase: GetKanjiQuizItemUseCase,
) : ViewModel() {

    private var _state = mutableStateOf(KanjiQuizItemState())
    val state: State<KanjiQuizItemState> = _state

    fun fetchQuizItem() {
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiQuizItemUseCase.invoke().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            error = result.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            loading = true,
                        )
                    }
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            kanjiQuizItem = result.data
                        )
                    }
                }
            }.launchIn(this)
        }
    }


}

data class KanjiQuizItemState(
    val kanjiQuizItem: List<KanjiQuizItem>? = null,
    val loading: Boolean = true,
    val error: String? = null
)