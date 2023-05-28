package com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.spaced


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KanjiCardItemSpaced(
    character: String,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.onBackground,
        elevation = 10.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable {
                onSelect(character)
            },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = character,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
            )
            Divider(
                Modifier
                    .align(Alignment.BottomCenter)
                    .padding(6.dp), thickness = 3.dp, color = Color.Green
            )
        }
    }
}


@Preview
@Composable
fun PreviewKanjiCard() {
    KanjiCardItemSpaced(character = "業", onSelect = {})
}