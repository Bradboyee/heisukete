package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.thepparat.heisukete.feature_kanjialive.presentation.util.TextDivider

@Composable
fun KanjiDetailScreen(kanji: String?, viewModel: KanjiDetailViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val detail = state.detail
    LaunchedEffect(key1 = true) {
        kanji?.let { viewModel.getKanjiDetail(kanji = it) }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        if (state.detail == null && !state.error.isNullOrEmpty()) {
            Text(text = state.error, color = Color.Red, textAlign = TextAlign.Center)
        }
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        detail?.let { kanji ->
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = kanji.character, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Meaning:" + kanji.meaning)
                Text(text = "ON readings:" + kanji.katakana)
                Text(text = "Kun readings:" + kanji.hiragana)
                TextDivider(text = "Info")
                Text(text = "Strokes:" + kanji.stroke.toString())
                TextDivider(text = "Compounds")
                kanji.examples?.forEach { example ->
                    Text(text = example)
                }
                TextDivider(text = "References")
            }
        }

    }


}