package com.thepparat.heisukete.space_repeat_feature.data.entity.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.R
import com.thepparat.heisukete.feature_kanjialive.domain.model.KanjiDetail
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.GetKanjiQuizItemUseCase
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.UpsertKanjiQuizItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpacedViewModel @Inject constructor(
    private val getKanjiQuizItemUseCase: GetKanjiQuizItemUseCase,
    private val upsertKanjiQuizItemUseCase: UpsertKanjiQuizItemUseCase
) : ViewModel() {



    fun fetchQuizItem() {
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiQuizItemUseCase.invoke().onEach {result ->
                when(result){
                    is Resource.Error -> {
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Log.i("fetchQuizItem",result.data.toString())
                    }
                }
            }.launchIn(this)
        }
    }


}