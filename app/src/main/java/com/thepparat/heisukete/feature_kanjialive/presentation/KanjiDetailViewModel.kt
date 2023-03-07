package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiDetailUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanjiDetailViewModel @Inject constructor(private val getKanjiDetailUseCase: GetKanjiDetailUseCase) :
    ViewModel() {

    var state = mutableStateOf(KanjiDetailState(loading = true))
        private set

    fun getKanjiDetail(kanji: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiDetailUseCase.invoke(kanji).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            loading = true
                        )
                    }
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            error = result.message,
                            loading = false
                        )
                    }
                    is Resource.Success -> {
                        state.value = state.value.copy(
                            detail = result.data,
                            loading = false
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