package com.thepparat.heisukete.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thepparat.heisukete.core.home.HomeScreen
import com.thepparat.heisukete.core.navigationbar.HeisukeScreen
import com.thepparat.heisukete.core.space.SpaceScreen
import com.thepparat.heisukete.core.stat.StatScreen
import com.thepparat.heisukete.feature_kanjialive.presentation.GridKanjiScreen
import com.thepparat.heisukete.feature_kanjialive.presentation.KanjiDetailScreen

@Composable
fun Navigate(navController: NavHostController, calculateBottomPadding: Dp) {
    NavHost(navController = navController, startDestination = HeisukeScreen.HomeScreen.route) {
        //space route
        composable(route = HeisukeScreen.SpacedScreen.route) {
            SpaceScreen()
        }
        //stat route
        composable(route = HeisukeScreen.StatScreen.route) {
            StatScreen()
        }
        //home route
        composable(route = HeisukeScreen.HomeScreen.route) {
            HomeScreen() { grade ->
                navController.navigate(HeisukeScreen.KanjiGrid.route + "/$grade")
            }
        }
        composable(route = HeisukeScreen.KanjiGrid.route + "/{grade}",
            arguments = listOf(navArgument(name = "grade") {
                type = NavType.IntType
            })) { backStackEntry ->
            GridKanjiScreen(backStackEntry.arguments?.getInt("grade"),
                bottomPadding = calculateBottomPadding) { character ->
                navController.navigate(HeisukeScreen.KanjiDetail.route + "/$character")
            }
        }
        composable(route = HeisukeScreen.KanjiDetail.route + "/{character}",
            arguments = listOf(navArgument(name = "character") {
                type = NavType.StringType
            })) { backStackEntry ->
            KanjiDetailScreen(backStackEntry.arguments?.getString("character"),
                bottomPadding = calculateBottomPadding)
        }
    }
}
