package com.thepparat.heisukete.feature_kanjialive.presentation.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest

@Composable
fun StrokeImage(url: String, description: String, boxSize: Dp) {
    val backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.75f)
    val drawLineAlpha = 0.9f
    Box(modifier = Modifier.size(size = boxSize)) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .border(width = 2.dp, color = backgroundColor)) {
            val width = size.width
            val height = size.height
            drawLine(
                alpha = drawLineAlpha,
                color = backgroundColor,
                start = Offset(x = width / 2, y = 0f),
                end = Offset(x = width / 2, y = height),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
            drawLine(
                alpha = drawLineAlpha,
                color = backgroundColor,
                start = Offset(x = 0f, y = height / 2),
                end = Offset(x = width, y = height / 2),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .decoderFactory(SvgDecoder.Factory())
                .crossfade(true)
                .build(),
            contentDescription = description,
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        )

    }

}