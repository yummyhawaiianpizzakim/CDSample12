package com.example.cdsample12

sealed class RooteGraph(val route: String) {

    object Route : RooteGraph("Route")
    object LoginNavGraph : RooteGraph("Login_NavGraph")
    object MainScreen : RooteGraph("Main_screen")
    object Admin : RooteGraph("Admin")

//    object LoginScreenPage : RooteGraph(route = "login_screen")
//    object RegisterScreenPage : RooteGraph("register_screen")


}