package com.thepparat.heisukete.feature_kanjialive.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TextDivider(text: String, space: Dp = 8.dp, modifier: Modifier = Modifier.fillMaxWidth()) {
    Spacer(modifier = Modifier.height(space))
    Box(
        modifier
            .background(color = MaterialTheme.colors.primary)
    ) {
        Text(
            text = text,
            Modifier
                .padding(start = 8.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(space))
}