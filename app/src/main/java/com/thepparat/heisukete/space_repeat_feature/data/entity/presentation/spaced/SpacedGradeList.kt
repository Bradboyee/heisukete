package com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.spaced

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thepparat.heisukete.space_repeat_feature.data.entity.domain.KanjiQuizItem

@Composable
fun SpacedGradeList(
    kanjiItems: List<KanjiQuizItem>,
    onSelect: (String) -> Unit,
    level: String
) {
    if (kanjiItems.isNotEmpty()) {
        Column(modifier = Modifier
            .padding(vertical = 8.dp)) {
            Text(
                text = "Spaced Repetition level : $level",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyHorizontalGrid(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(if (kanjiItems.size > 1) 180.dp else 82.dp),
                rows = GridCells.Fixed(if (kanjiItems.size > 1) 2 else 1),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(kanjiItems) {
                    KanjiCardItemSpaced(character = it.character) { character ->
                        onSelect(character)
                    }
                }
            }
        }
    }
}