package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.thepparat.heisukete.feature_kanjialive.presentation.util.StrokeImage
import com.thepparat.heisukete.feature_kanjialive.presentation.util.TextDivider

@Composable
fun KanjiDetailScreen(
    kanji: String?,
    paddingValues: PaddingValues,
    viewModel: KanjiDetailViewModel = hiltViewModel(),
) {

    val adapt = 48.dp
    var boxWidth by remember { mutableStateOf(Size.Zero) }
    val state = viewModel.state.value
    val detail = state.detail
    LaunchedEffect(key1 = true) {
        kanji?.let { viewModel.getKanjiDetail(kanji = it) }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .padding(16.dp)
        .onGloballyPositioned { layoutCoordinates ->
            boxWidth = layoutCoordinates.size.toSize()
        },
        contentAlignment = Alignment.TopStart) {
        if (state.detail == null && !state.error.isNullOrEmpty()) {
            Text(text = state.error, color = Color.Red, textAlign = TextAlign.Center)
        }
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        detail?.let { kanji ->
            val height = calculateImagesStrokeHeight(count = kanji.images?.size ?: 1)
            Log.d("HEIGHT", height.toString())
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Text(
                        style = MaterialTheme.typography.h1,
                        text = kanji.character,
                        fontSize = 64.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.Top)
                            .padding(horizontal = 16.dp)
                    )
                    LazyVerticalGrid(modifier = Modifier
                        .height(height.dp)
                        .align(alignment = Alignment.Top)
                        .weight(1f),
                        columns = GridCells.Adaptive(minSize = adapt),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (kanji.images != null) {
                            itemsIndexed(kanji.images) { index, url ->
                                StrokeImage(url = url,
                                    description = "Stroke $index",
                                    boxSize = adapt)
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colors.primary)
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .align(alignment = Alignment.Start),
                ) {
                    listOf(
                        "Meaning:" to " ${kanji.meaning}",
                        "ON readings:" to " ${kanji.katakana}",
                        "Kun readings:" to " ${kanji.hiragana}",
                        "Strokes:" to " ${kanji.stroke}",
                        "Grade:" to " ${kanji.grade}",
                    ).forEach { (verb, pronoun) ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = verb,
                                color = MaterialTheme.colors.primary,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .weight(0.5f)
                                    .fillMaxWidth()
                                    .wrapContentWidth(align = Alignment.End)
                                    .align(alignment = Alignment.Top)
                            )
                            Text(
                                text = pronoun,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            )
                        }
                        if (verb == "Kun readings:") {
                            TextDivider(text = "Info", modifier = Modifier.width(boxWidth.width.dp))

                        }
                    }
                }
                TextDivider(text = "Compounds")
                kanji.examples?.forEach { example ->
                    Text(text = example)
                }

            }


        }
    }
}

private fun calculateImagesStrokeHeight(count: Int, range: Int = 4): Int {
    val rate = 60
    return when (count) {
        0 -> 1 * rate
        else -> ((count - 1) / range + 1) * rate
    }
}
