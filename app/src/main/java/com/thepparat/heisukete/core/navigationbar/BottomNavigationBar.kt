package com.thepparat.heisukete.core.navigationbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<NavItem>,
    navController: NavController,
    onItemClick: (NavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = 5.dp) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = MaterialTheme.colors.background,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                })
        }
    }
}

val NAVIGATION_ITEMS = listOf(
    NavItem(
        name = HeisukeScreen.SpacedScreen.name,
        route = HeisukeScreen.SpacedScreen.route,
        icon = Icons.Default.Refresh,
        badgeCount = 1
    ),
    NavItem(
        name = HeisukeScreen.HomeScreen.name,
        route = HeisukeScreen.HomeScreen.route,
        icon = Icons.Default.Home,
        badgeCount = 2
    ),
    NavItem(
        name = HeisukeScreen.StatScreen.name,
        route = HeisukeScreen.StatScreen.route,
        icon = Icons.Default.Star,
        badgeCount = 3
    ),
)