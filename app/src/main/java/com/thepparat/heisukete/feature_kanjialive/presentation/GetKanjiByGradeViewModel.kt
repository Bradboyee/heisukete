package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetKanjiByGradeUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetKanjiByGradeViewModel @Inject constructor(
    private val getKanjiByGradeUseCase: GetKanjiByGradeUseCase,
) : ViewModel() {

    var state = mutableStateOf(KanjiByGradeState())
        private set

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            Log.d("QUERY", query)
        }
    }

    fun loadKanjiByGrade(grade: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiByGradeUseCase.invoke(grade).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            error = result.message,
                            loading = false
                        )
                    }
                    is Resource.Success -> {
                        state.value = state.value.copy(
                            kanji = result.data,
                            loading = false
                        )
                    }
                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            loading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}