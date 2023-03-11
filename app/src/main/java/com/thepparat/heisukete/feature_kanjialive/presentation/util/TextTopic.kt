package com.thepparat.heisukete.feature_kanjialive.presentation.util

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TextTopic(topic: String, desc: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .align(alignment = Alignment.Top)
        )
        Text(
            text = desc,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )
    }
}