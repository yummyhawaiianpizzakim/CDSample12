package com.example.cdsample12.bottombar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Person
//import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cdsample12.Admin.AdminPage
import com.example.cdsample12.Admin.AdminRefuseAppro
import com.example.cdsample12.Admin.LectureRoomManage
import com.example.cdsample12.UIitem.SelectableSettingGroupItem

@Composable
fun SettingsAdmin(
    navController: NavController,
    onRAClick: ()->Unit,
    onRMClick: ()->Unit
) {

    Scaffold(

        content = {

            Column(
                modifier = Modifier
                    .padding(it)
            ) {
//                NavigationAdminHost(navController = navController)


                Spacer(modifier = Modifier.height(100.dp))

                Text(text = "Account")
                SelectableSettingGroupItem(
                    title = "Sign Out",
                    icon = Icons.Outlined.Person
                ) {
                    }
                Spacer(modifier = Modifier.height(100.dp))
                Text(text = "Admin")
                SelectableSettingGroupItem(
                    title = "Waiting Request",
                    icon = Icons.Outlined.AccountCircle,
                ) {
                    onRAClick()
//                    navController.popBackStack()
//
//                    navController.navigate(ScreenPage.AdminRefuseAppro.route) {
////                        popUpTo(navController.graph.startDestinationId)
//
//                        launchSingleTop = true
//                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                SelectableSettingGroupItem(
                    title = "Manage Room",
                    icon = Icons.Outlined.AccountCircle
                ) {
                    onRMClick()
                }
            }
        }
    )
}

//
//@Composable
//fun NavigationAdminHost(
//    navController: NavController,
////    logout: () -> Unit
//) {
//    NavHost(
//        navController = navController as NavHostController,
//        startDestination = AdminPage.AdminRefuseAppro.route
//    ) {
//        composable(AdminPage.AdminRefuseAppro.route) { AdminRefuseAppro() }
//        composable(AdminPage.LectureRoomManage.route) { LectureRoomManage() }
//
//    }
//}
