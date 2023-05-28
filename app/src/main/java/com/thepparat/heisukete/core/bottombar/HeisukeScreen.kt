package com.thepparat.heisukete.core.bottombar

sealed class HeisukeScreen(val name: String, val route: String) {
    object SpacedScreen : HeisukeScreen("Spaced", "spaced_screen")
    object StatScreen : HeisukeScreen("Stat", "stat_screen")
    object HomeScreen : HeisukeScreen("Home", "home_screen")
    object KanjiGrid : HeisukeScreen("Grid", "home_grid_screen")
    object KanjiDetail : HeisukeScreen("KanjiDetail", "kanji_detail")
    object QuizScreen : HeisukeScreen("Quiz","kanji_quiz")
}
