package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
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

    fun getKanjiDetail(kanji: String) {
        viewModelScope.launch {
            when (val result = getKanjiDetailUseCase.invoke(kanji)) {
                is Resource.Error -> Log.e("DETAIL", result.message.toString())
                is Resource.Success -> Log.d("DETAIL", result.data.toString())
            }
        }
    }
}

data class KanjiDetailState(
    val detail : KanjiDetail
)