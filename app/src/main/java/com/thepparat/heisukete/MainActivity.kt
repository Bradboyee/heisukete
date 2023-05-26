package com.thepparat.heisukete

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thepparat.heisukete.core.Navigate
import com.thepparat.heisukete.core.bottombar.BottomNavigationBar
import com.thepparat.heisukete.core.topbar.TopBar
import com.thepparat.heisukete.core.topbar.TopBarViewModel
import com.thepparat.heisukete.ui.theme.HeisuketeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeisuketeTheme {
                val topBarViewModel: TopBarViewModel = hiltViewModel()
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val scaffoldState = rememberScaffoldState()
                Scaffold(scaffoldState = scaffoldState,
                    bottomBar = {
                        BottomNavigationBar(navController = navController, onItemClick = {
                            navController.navigate(it.route)
                        })
                    },
                    topBar = {
                        TopBar(
                            navController = navController,
                            viewModel = topBarViewModel
                        )
                    }) {
                    Navigate(navController = navController, it, topBarViewModel = topBarViewModel,scaffoldState = scaffoldState)
                }
            }
        }
    }

    @Composable
    inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): ViewModel {
        val navGraphRoute = destination.parent?.route ?: viewModel()
        val parentEntry = remember(this) {
            navController.getBackStackEntry(navGraphRoute)
        }
        return viewModel(parentEntry)
    }
}








