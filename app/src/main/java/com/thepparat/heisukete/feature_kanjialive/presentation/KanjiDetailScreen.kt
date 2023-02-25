package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun KanjiDetailScreen(kanji: String?, viewModel: KanjiDetailViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = true) {
        kanji?.let { viewModel.getKanjiDetail(kanji = it) }
    }
}