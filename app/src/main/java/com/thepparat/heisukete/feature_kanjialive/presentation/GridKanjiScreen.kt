package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun GridKanjiScreen(
    grade: Int?,
    viewModel: GetKanjiByGradeViewModel = hiltViewModel(),
    onSelect: (String) -> Unit,
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true, block = {
        grade?.let { viewModel.loadKanjiByGrade(grade = it) }
    })
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.loading) {
            Log.d("LOADING", "KANJI SCREEN")
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.primary)

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

    }
}