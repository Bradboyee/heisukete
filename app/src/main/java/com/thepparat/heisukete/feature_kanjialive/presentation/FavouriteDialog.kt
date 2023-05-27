package com.thepparat.heisukete.feature_kanjialive.presentation

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.thepparat.heisukete.core.topbar.TopBarViewModel

@Composable
fun FavouriteDialog(viewModel: TopBarViewModel) {
    AlertDialog(onDismissRequest = { viewModel.dismissDialog() },
        title = {
            Text(text = "Are you sure.")
        },
        text = {
            Text(text = "the spaced repetition level will be lost. ")
        },
        confirmButton = {
            Button(
                onClick = {
                    viewModel.delete()
                    viewModel.dismissDialog()
                }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    viewModel.dismissDialog()
                }) {
                Text("CANCEL")
            }
        })
}