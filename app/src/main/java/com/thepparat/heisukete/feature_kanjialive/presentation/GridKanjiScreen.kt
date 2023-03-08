package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GridKanjiScreen(
    grade: Int?,
    paddingValues: PaddingValues,
    viewModel: GetKanjiByGradeViewModel = hiltViewModel(),
    onSelect: (String) -> Unit,
) {
    Log.d("DETAIL SCREEN", "SELECT GRADE $grade")
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        refreshing = false
    }

    val refreshState = rememberPullRefreshState(refreshing = refreshing, onRefresh = ::refresh)
    val state = viewModel.state.value
    LaunchedEffect(key1 = true, block = {
        grade?.let {
            viewModel.grade.value = it
        }
        grade?.let { viewModel.loadKanjiByGrade(grade = it) }
    })
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .pullRefresh(refreshState),
        contentAlignment = Alignment.Center) {
        if (state.loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.align(alignment = Alignment.Center))

        }
        if (!state.error.isNullOrEmpty()) {
            Text(text = state.error, color = Color.Red, textAlign = TextAlign.Center)
        }
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = viewModel.searchQuery.value,
                onValueChange = viewModel::onSearch,
                placeholder = {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                        Text(text = "Search",
                            modifier = Modifier
                                .align(alignment = Alignment.Bottom)
                                .padding(start = 16.dp))
                    }
                })
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
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
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        PullRefreshIndicator(refreshing, refreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(showBackground = true)
@Composable
fun previewGridScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

    }
}