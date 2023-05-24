package com.example.quizrank.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quizrank.data.auth.AuthService
import com.example.quizrank.feature.quiz_auth.login.LoginScreen
import com.example.quizrank.feature.quiz_auth.register.RegisterScreen
import com.example.quizrank.feature.quiz_main.MainScreen
import com.example.quizrank.feature.quiz_questions.QuestionsScreen
import com.example.quizrank.feature.quiz_topics.TopicsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    authService: AuthService
) {
    val hasUser = authService.hasUser

    NavHost(
        navController = navController,
        startDestination = if(hasUser) Screen.Main.route else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onSuccess = {
                    navController.navigate(Screen.Main.route)
                },
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onNavigateBack = {
                    navController.popBackStack(
                        route = Screen.Login.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.Login.route)
                },
                onSuccess = {
                    navController.navigate(Screen.Main.route)
                }
            )
        }
        composable(Screen.Main.route) {
            MainScreen(
                onSignOut = {
                    navController.popBackStack(
                        route = Screen.Login.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.Login.route)
                },
                onPlayClick =  {
                    navController.popBackStack(
                        route = Screen.Topics.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.Topics.route)
                }
            )
        }
        composable(Screen.Topics.route){
            TopicsScreen(
                onListItemClick = {
                    navController.navigate(Screen.Questions.passId(it))
                },
                onQuit = {
                    navController.popBackStack(
                        route = Screen.Main.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.Main.route)
                }
            )
        }
        composable(
            route = Screen.Questions.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            QuestionsScreen(
                onQuit = {
                    navController.popBackStack(
                        route = Screen.Main.route,
                        inclusive = true
                    )
                    navController.navigate(Screen.Main.route)
                }
            )
        }
    }
}