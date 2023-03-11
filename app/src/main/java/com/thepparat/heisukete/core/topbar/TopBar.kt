package com.thepparat.heisukete.core.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.thepparat.heisukete.core.bottombar.HeisukeScreen

@Composable
fun TopBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    TopAppBar() {
        if (backStackEntry.value?.destination?.route != HeisukeScreen.HomeScreen.route)
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.padding(start = 16.dp).clickable {
                    navController.popBackStack()
                })
    }
}