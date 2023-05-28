package com.thepparat.heisukete.feature_kanjialive.presentation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.thepparat.heisukete.R
import com.thepparat.heisukete.core.Banner
import com.thepparat.heisukete.core.topbar.FavouriteEvent
import com.thepparat.heisukete.core.topbar.TopBarEvent
import com.thepparat.heisukete.core.topbar.TopBarViewModel
import com.thepparat.heisukete.feature_kanjialive.presentation.util.StrokeImage
import com.thepparat.heisukete.feature_kanjialive.presentation.util.TextDivider
import com.thepparat.heisukete.feature_kanjialive.presentation.util.TextTopic
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem
import kotlinx.coroutines.flow.collectLatest
import java.util.Date

@Composable
fun KanjiDetailScreen(
    kanji: String?,
    paddingValues: PaddingValues,
    viewModel: KanjiDetailViewModel = hiltViewModel(),
    topBarViewModel: TopBarViewModel,
    scaffoldState: ScaffoldState
) {
    val adapt = 48.dp
    var boxWidth by remember { mutableStateOf(Size.Zero) }
    val state = viewModel.state.value
    val detail = state.detail
    val openDialog = topBarViewModel.openDialog
    LaunchedEffect(key1 = true) {
        kanji?.let { viewModel.getKanjiDetail(kanji = it) }
        topBarViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is TopBarEvent.ShowMessage -> {
                    scaffoldState.snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
            .onGloballyPositioned { layoutCoordinates ->
                boxWidth = layoutCoordinates.size.toSize()
            },
        contentAlignment = Alignment.TopStart
    ) {
        if (state.detail == null && !state.error.isNullOrEmpty()) {
            Text(text = state.error, color = Color.Red, textAlign = TextAlign.Center)
        }
        if (state.loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        detail?.let { kanji ->
            if (openDialog.value) {
                FavouriteDialog(viewModel = topBarViewModel)
            }
            val kanjiQuizItem = KanjiQuizItem(
                character = kanji.character,
                grade = kanji.grade,
                meaning = kanji.meaning,
                create_dt = Date(),
                update_dt = Date(),
                repeat_level = 0,
                do_date = Date()
            )
            //check TopBar Star status isSelected
            topBarViewModel.onEvent(FavouriteEvent.CheckKanjiIsSelected(character = kanji.character))
            topBarViewModel.onEvent(FavouriteEvent.SetKanjiQuizItem(kanjiQuizItem = kanjiQuizItem))
            //set value to top bar

            val height = calculateImagesStrokeHeight(count = kanji.images?.size ?: 1)
            Log.d("HEIGHT", height.toString())
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
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
                    LazyVerticalGrid(
                        modifier = Modifier
                            .height(height.dp)
                            .align(alignment = Alignment.Top)
                            .weight(1f),
                        columns = GridCells.Adaptive(minSize = adapt),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (kanji.images != null) {
                            itemsIndexed(kanji.images) { index, url ->
                                StrokeImage(
                                    url = url,
                                    description = "Stroke $index",
                                    boxSize = adapt
                                )
                            }
                        }
                    }

                }
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                ) {
                    Banner(stringResource(R.string.unit_ID))
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
                        TextTopic(topic = verb, desc = pronoun)
                        if (verb == "Kun readings:") {
                            TextDivider(text = "Info", modifier = Modifier.width(boxWidth.width.dp))

                        }
                    }
                }
                TextDivider(text = "Compounds")
                kanji.examples?.forEach { example ->
                    Text(text = example)
                }
                TextDivider(text = "Radicals")
                Row(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(kanji.radicalImage)
                            .decoderFactory(SvgDecoder.Factory())
                            .crossfade(true)
                            .build(),
                        contentDescription = kanji.radical,
                        modifier = Modifier
                            .align(alignment = Alignment.CenterVertically)
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    )
                    Column(
                        modifier = Modifier
                            .width(IntrinsicSize.Max)
                            .weight(3f)
                    ) {
                        TextTopic(topic = "stroke: ", desc = kanji.radicalStroke.toString())
                        TextTopic(topic = "hiragana: ", desc = kanji.radicalHiragana)
                        TextTopic(topic = "romaji: ", desc = kanji.radicalRomaji)
                    }

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

