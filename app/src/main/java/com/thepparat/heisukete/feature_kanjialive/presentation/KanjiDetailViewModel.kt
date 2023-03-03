package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiDetailUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KanjiDetailViewModel @Inject constructor(private val getKanjiDetailUseCase: GetKanjiDetailUseCase) :
    ViewModel() {

    var state = mutableStateOf(KanjiDetailState(loading = true))
        private set

    fun getKanjiDetail(kanji: String) {
        viewModelScope.launch {
            when (val result = getKanjiDetailUseCase.invoke(kanji)) {
                is Resource.Error -> state.value = state.value.copy(
                    detail = null,
                    loading = false,
                    error = result.message
                )
                is Resource.Success -> state.value = state.value.copy(
                    detail = result.data,
                    loading = false,
                    error = null
                )
            }
        }
    }
}

data class KanjiDetailState(
    val detail: KanjiDetail? = null,
    val error: String? = null,
    val loading: Boolean,
)