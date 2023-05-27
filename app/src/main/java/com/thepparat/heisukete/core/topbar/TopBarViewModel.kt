package com.thepparat.heisukete.core.topbar

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thepparat.heisukete.R
import com.thepparat.heisukete.feature_kanjialive.domain.util.Resource
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.DeleteKanjiQuizItemUseCase
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.GetKanjiQuizItemByCharacterUseCase
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.usecase.UpsertKanjiQuizItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "TopBarView "

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val upsertKanjiQuizItemUseCase: UpsertKanjiQuizItemUseCase,
    private val getKanjiQuizItemByCharacterUseCase: GetKanjiQuizItemByCharacterUseCase,
    private val deleteKanjiQuizItemUseCase: DeleteKanjiQuizItemUseCase
) :
    ViewModel() {
    private var _isFavouriteIconState = mutableStateOf(R.drawable.baseline_star_outline_24)
    val isFavouriteIconState: State<Int> = _isFavouriteIconState

    private val _uiEventFlow = MutableSharedFlow<TopBarEvent>()
    val uiEventFlow = _uiEventFlow.asSharedFlow()

    private var _isSelect = mutableStateOf(false)

    private var kanjiQuizItem: KanjiQuizItem? = null

    private var _openDialog = mutableStateOf(false)
    val openDialog: State<Boolean> = _openDialog

    fun dismissDialog() {
        _openDialog.value = false
    }

    private fun openDialog() {
        _openDialog.value = true
    }

    fun onEvent(event: FavouriteEvent) {
        when (event) {
            FavouriteEvent.OnFavouriteClick -> {
                onClickFavIcon()
            }

            is FavouriteEvent.SetKanjiQuizItem -> {
                this.kanjiQuizItem = event.kanjiQuizItem
            }

            is FavouriteEvent.CheckKanjiIsSelected -> {
                checkIsSelected(character = event.character)
            }
        }
    }

    private fun onClickFavIcon() {
        val prefix = "onClickFavIcon : "
        Log.d(TAG,"onClickFavIcon execute")
        viewModelScope.launch(Dispatchers.IO) {
            val kanjiQuizItem = kanjiQuizItem
                ?: return@launch// Store kanjiQuizItem in a local variable
            Log.d(TAG, prefix + "kanjiQuizItem")
            Log.d(TAG, prefix + "click")
            _isSelect.value = !_isSelect.value
            if (_isSelect.value) {
                Log.d(TAG, prefix + "selected")
                insert(kanjiQuizItem)
                _uiEventFlow.emit(TopBarEvent.ShowMessage(message = "add success."))
                changeIconByState()
            } else {
                Log.d(TAG, prefix + "not selected")
                openDialog()
            }
        }
    }

    private fun checkIsSelected(character: String) {
        val prefix = "checkIsSelected "
        Log.d(TAG,"checkIsSelected execute")
        viewModelScope.launch(Dispatchers.IO) {
            getKanjiQuizItemByCharacterUseCase.invoke(character).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _isSelect.value = false
                        Log.d(TAG, "$prefix failed : ${result.message}")
                    }

                    is Resource.Loading -> {
                        Log.d(TAG, "$prefix checking..")
                    }

                    is Resource.Success -> {
                        _isSelect.value = true
                        Log.d(TAG, "$prefix success.")
                    }
                }
                Log.d(TAG, "_isSelect : $_isSelect")
                changeIconByState()
            }.launchIn(this)
        }
    }


    private fun changeIconByState() {
        _isFavouriteIconState.value = if (_isSelect.value) {
            R.drawable.baseline_star_24
        } else {
            R.drawable.baseline_star_outline_24
        }
    }

    private fun insert(kanjiQuizItem: KanjiQuizItem) {
        val prefix = "insert"
        viewModelScope.launch(Dispatchers.IO) {
            upsertKanjiQuizItemUseCase.invoke(kanjiQuizItem).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d(TAG, prefix + "ERROR")
                    }

                    is Resource.Loading -> {
                        Log.d(TAG, prefix + "Loading")
                    }

                    is Resource.Success -> {
                        Log.d(TAG, prefix + "Success")
                    }
                }
            }.launchIn(this)
        }
    }

     fun delete() {
        val prefix = "delete : "
        viewModelScope.launch(Dispatchers.IO) {
            val kanjiQuizItem = kanjiQuizItem
                ?: return@launch// Store kanjiQuizItem in a local variable
            deleteKanjiQuizItemUseCase.invoke(kanjiQuizItem).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        Log.d(TAG, prefix + "ERROR")
                    }

                    is Resource.Loading -> {
                        Log.d(TAG, prefix + "Loading")
                    }

                    is Resource.Success -> {
                        Log.d(TAG, prefix + "Success")
                        _isSelect.value = false
                        _uiEventFlow.emit(TopBarEvent.ShowMessage(message = "delete success."))
                    }
                }
            }.launchIn(this)
        }
    }
}
