package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KanjiCardItem(character: String, modifier: Modifier = Modifier, onSelect: (String) -> Unit) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable {
                onSelect(character)
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(4.dp)) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()) {
            Text(
                text = character,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,

                )
        }
    }
}