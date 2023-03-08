package com.thepparat.heisukete.core.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(clickHandler: (Int) -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
        LazyColumn {
            items(6) {
                val grade = it + 1
                GradeCard(grade = grade,
                    description = "beginner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            clickHandler(grade)
                        })
                Divider(color = MaterialTheme.colors.primary)
            }
        }
    }
}


@Composable
fun GradeCard(grade: Int, description: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = MaterialTheme.shapes.medium) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Text(text = "Grade $grade",
                    style = MaterialTheme.typography.h4)
                Text(text = description, style = MaterialTheme.typography.body1)
            }
        }
    }
}