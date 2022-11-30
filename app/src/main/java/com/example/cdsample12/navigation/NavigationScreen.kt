package com.example.cdsample12.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cdsample12.R

//import com.example.loginbottombarapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object HomeScreen : BottomBarScreen(
        route = "home_screen",
        title = "Home",
        icon = Icons.Default.Home
    )

    object ProfileScreen : BottomBarScreen(
        route = "profile_screen",
        title = "Profile",
        icon = Icons.Default.Home
    )

    object SettingsScreen : BottomBarScreen(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Default.Home
    )

    object SettingsAdminScreen : BottomBarScreen(
        route = "settingsAdmin_screen",
        title = "SettingsAdmin",
        icon = Icons.Default.Home
    )
    object ReservingRoom : BottomBarScreen(
        route = "Reserving_Room",
        title = "ReservingRoom",
        icon = Icons.Default.Home
    )

//    object AdminRefuseAppro : NavigationScreen(
//        route = "AdminRefuse_Appro",
//        title = "AdminRefuseAppro",
//        icon = R.drawable.ic_home
//    )
//
//    object LectureRoomManage : NavigationScreen(
//        route = "LectureRoom_Manage",
//        title = "LectureRoomManage",
//        icon = R.drawable.ic_home
//    )

}