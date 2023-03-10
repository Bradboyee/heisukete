package com.thepparat.heisukete.core.bottombar

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val name: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount: Int = 0,
)
