package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GridKanjiScreen(
    grade: Int?,
    viewModel: GetKanjiByGradeViewModel = hiltViewModel(),
    onSelect: (String) -> Unit,
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount += 5
        refreshing = false
    }
    val refreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh =  ::refresh)
    val state = viewModel.state.value
    LaunchedEffect(key1 = true, block = {
        grade?.let { viewModel.loadKanjiByGrade(grade = it) }
    })
    Box(modifier = Modifier.fillMaxSize().pullRefresh(refreshState), contentAlignment = Alignment.Center) {
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.primary)

        }
        if (!state.error.isNullOrEmpty()) {
            Text(text = state.error, color = Color.Red, textAlign = TextAlign.Center)
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(8.dp),
            columns = GridCells.Adaptive(minSize = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            state.kanji?.let {
                items(it) { kanji ->
                    KanjiCardItem(character = kanji.kanji) { character ->
                        onSelect(character)
                    }
                }
            }
        }
        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }
}