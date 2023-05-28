package com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.spaced

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SpaceScreen(
    viewModel: SpacedViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onSelect: (String) -> Unit,
    onStartQuiz : () -> Unit
) {
    val state = viewModel.state.value
    LaunchedEffect(key1 = true) {
        viewModel.fetchQuizItem()
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues = paddingValues), contentAlignment = Alignment.Center) {
        //error
        if (state.kanjiQuizItem == null && !state.error.isNullOrEmpty()) {
            Text(
                text = state.error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        //loading
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        state.kanjiQuizItem?.let { kanjiItems ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item { 
                    Button(onClick = { onStartQuiz() }) {
                        Text(text = "Start")
                    }
                }
                items(count = 5){
                    val level = it + 1
                    SpacedGradeList(
                        kanjiItems = kanjiItems.filter { kanji -> kanji.grade == level },
                        onSelect = onSelect,
                        level = level.toString()
                    )
                }
            }
        }

    }
}