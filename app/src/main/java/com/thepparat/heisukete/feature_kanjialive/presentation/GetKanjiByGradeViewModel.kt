package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetByGradeUseCase
import com.thepparat.heisukete.feature_kanjialive.domain.usecase.GetBySearchUseCase
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
    private val getByGradeUseCase: GetByGradeUseCase,
    private val searchUseCase: GetBySearchUseCase,
) : ViewModel() {
    var state = mutableStateOf(KanjiByGradeState())
        private set
    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery
    private var searchJob: Job? = null

    private val _grade = mutableStateOf(0)
    val grade: MutableState<Int> = _grade

    val setGrade: (Int) -> Unit = { grade: Int ->
        _grade.value = grade
    }

    fun onSearch(query: String) {
        _searchQuery.value = query
        Log.d("SEARCH GRID", "SEARCH WITH QUERY : ${_searchQuery.value} GRADE : ${_grade.value}")
        searchJob?.cancel()
        searchJob = viewModelScope.launch(Dispatchers.IO) {
            delay(500)
            searchUseCase.invoke(grade = _grade.value, query = query).onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        state.value = state.value.copy(
                            loading = true,
                            kanji = null,
                            error = null
                        )
                    }
                    is Resource.Error -> {
                        state.value = state.value.copy(
                            error = result.message,
                            loading = false,
                            kanji = null
                        )
                    }
                    is Resource.Success -> {
                        state.value = state.value.copy(
                            kanji = result.data,
                            error = null,
                            loading = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}