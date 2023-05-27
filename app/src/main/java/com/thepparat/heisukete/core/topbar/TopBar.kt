package com.thepparat.heisukete.core.topbar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thepparat.heisukete.core.bottombar.HeisukeScreen

@Composable
fun TopBar(navController: NavController, viewModel: TopBarViewModel) {
    val painter = painterResource(viewModel.isFavouriteIconState.value)
    val backStackEntry = navController.currentBackStackEntryAsState()
    TopAppBar(elevation = 4.dp) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val destinationRoute = backStackEntry.value?.destination?.route
            Log.i("destinationRoute", destinationRoute.toString())
            if (destinationRoute != HeisukeScreen.HomeScreen.route)
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable {
                            navController.popBackStack()
                        })
            if (destinationRoute?.split("/")?.get(0) == HeisukeScreen.KanjiDetail.route) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .clickable {
                            viewModel.onEvent(event = FavouriteEvent.OnFavouriteClick)
                        },
                    painter = painter, contentDescription = "Back",
                )
            }

        }
    }
}