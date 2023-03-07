package com.thepparat.heisukete.core.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(clickHandler: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LazyColumn {
            items(5) {
                val grade = it + 1
                Text(text = "Grade $grade", modifier = Modifier.clickable {
                    Log.i("CLICK GRADE", (it + 1).toString())
                    clickHandler(grade)
                })
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}