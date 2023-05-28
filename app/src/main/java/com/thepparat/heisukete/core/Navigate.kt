package com.thepparat.heisukete.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.thepparat.heisukete.core.bottombar.HeisukeScreen
import com.thepparat.heisukete.core.home.HomeScreen
import com.thepparat.heisukete.core.stat.StatScreen
import com.thepparat.heisukete.core.topbar.TopBarViewModel
import com.thepparat.heisukete.feature_kanjialive.presentation.GridKanjiScreen
import com.thepparat.heisukete.feature_kanjialive.presentation.KanjiDetailScreen
import com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.quiz.QuizScreen
import com.thepparat.heisukete.space_repeat_feature.data.entity.presentation.spaced.SpaceScreen


@Composable
fun Navigate(
    navController: NavHostController,
    paddingValues: PaddingValues,
    topBarViewModel: TopBarViewModel,
    scaffoldState: ScaffoldState
) {
    NavHost(navController = navController, startDestination = HeisukeScreen.HomeScreen.route) {
        //space route
        composable(route = HeisukeScreen.SpacedScreen.route) {
            SpaceScreen(paddingValues = paddingValues, onSelect = { character ->
                navController.navigate(HeisukeScreen.KanjiDetail.route + "/$character")
            }, onStartQuiz = {
                navController.navigate(HeisukeScreen.QuizScreen.route)
            })
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
        composable(
            route = HeisukeScreen.KanjiGrid.route + "/{grade}",
            arguments = listOf(navArgument(name = "grade") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            backStackEntry.destination.parent
            GridKanjiScreen(
                backStackEntry.arguments?.getInt("grade"),
                paddingValues = paddingValues
            ) { character ->
                navController.navigate(HeisukeScreen.KanjiDetail.route + "/$character")
            }
        }
        composable(
            route = HeisukeScreen.KanjiDetail.route + "/{character}",
            arguments = listOf(navArgument(name = "character") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            KanjiDetailScreen(
                backStackEntry.arguments?.getString("character"),
                paddingValues = paddingValues,
                topBarViewModel = topBarViewModel,
                scaffoldState = scaffoldState
            )
        }
        composable(route = HeisukeScreen.QuizScreen.route) {
            QuizScreen(paddingValues = paddingValues)
        }
    }
}

