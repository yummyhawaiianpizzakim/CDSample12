package com.example.cdsample12.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.cdsample12.RooteGraph
import com.talhaoz.loginbottombarapp.ui.login.LoginScreen
import com.talhaoz.loginbottombarapp.ui.login.RegisterScreen

fun NavGraphBuilder.LoginNavGraph(navController: NavHostController) {
    navigation(
        route = RooteGraph.LoginNavGraph.route,
        startDestination = LoginScreen.Login.route
    ) {
        composable(route = LoginScreen.Login.route) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(RooteGraph.MainScreen.route)
                },
                onForgotClick = {
                    navController.navigate(LoginScreen.Forgot.route)
                },
                onRegisterClick = {
                    navController.popBackStack()
                    navController.navigate(LoginScreen.Register.route)
                }
            )
        }
        composable(route = LoginScreen.Register.route) {
            RegisterScreen(
                navController = rememberNavController(),
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(LoginScreen.Login.route)
                }
            )
        }
//        composable(route = LoginScreen.Forgot.route) {
//            ScreenContent(name = LoginScreen.Forgot.route) {}
//        }
    }
}

sealed class LoginScreen(val route: String) {
    object Login : LoginScreen(route = "LOGIN")
    object Forgot : LoginScreen(route = "Forgot")
    object Register : LoginScreen(route = "Register")
}