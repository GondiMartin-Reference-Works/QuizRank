package com.example.quizrank.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Register: Screen("register")
    object Main: Screen("main")
    object Topics: Screen("topics")
}
