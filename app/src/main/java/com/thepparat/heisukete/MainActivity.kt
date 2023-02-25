package com.thepparat.heisukete

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.thepparat.heisukete.core.Navigate
import com.thepparat.heisukete.core.navigationbar.BottomNavigationBar
import com.thepparat.heisukete.core.navigationbar.NAVIGATION_ITEMS
import com.thepparat.heisukete.feature_kanjialive.presentation.GetKanjiByGradeViewModel
import com.thepparat.heisukete.ui.theme.HeisuketeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeisuketeTheme {
                val navController = rememberNavController()

                Scaffold(bottomBar = {
                    BottomNavigationBar(
                        items = NAVIGATION_ITEMS,
                        navController = navController,
                        onItemClick = {
                            navController.navigate(it.route)
                        }
                    )
                }) {
                    Navigate(navController = navController)
                }
            }
        }
    }
}








