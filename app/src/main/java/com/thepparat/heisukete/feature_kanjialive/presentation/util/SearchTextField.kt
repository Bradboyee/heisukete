package com.thepparat.heisukete.feature_kanjialive.presentation.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchKanjiTextField(modifier: Modifier = Modifier, value: String, onChange: (String) -> Unit) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onChange,
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = "Search")
        },
        placeholder = { Text(text = "Search") }
    )
}