package com.thepparat.heisukete

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thepparat.heisukete.core.Navigate
import com.thepparat.heisukete.core.navigationbar.BottomNavigationBar
import com.thepparat.heisukete.core.navigationbar.HeisukeScreen
import com.thepparat.heisukete.core.navigationbar.NAVIGATION_ITEMS
import com.thepparat.heisukete.ui.theme.HeisuketeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeisuketeTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        items = NAVIGATION_ITEMS,
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }, topBar = {
                    TopAppBar {
                        if (backStackEntry.value?.destination?.route != HeisukeScreen.HomeScreen.route)
                            Icon(imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.padding(start = 16.dp).clickable {
                                    navController.popBackStack()
                                })
                    }
                }) {
                    Navigate(navController = navController,it.calculateBottomPadding())
                }
            }
        }
    }
}








