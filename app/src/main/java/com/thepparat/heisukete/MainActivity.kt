package com.thepparat.heisukete

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thepparat.heisukete.core.Navigate
import com.thepparat.heisukete.core.bottombar.BottomNavigationBar
import com.thepparat.heisukete.core.bottombar.NAVIGATION_ITEMS
import com.thepparat.heisukete.core.topbar.TopBar
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
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }, topBar = { TopBar(navController = navController) }
                ) {
                    Navigate(navController = navController,it)
                }
            }
        }
    }
}








