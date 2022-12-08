package com.example.cdsample12

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cdsample12.Admin.AdminRefuseAppro
import com.example.cdsample12.bottombar.MainScreen
import com.example.cdsample12.bottombar.SettingsAdmin
import com.example.cdsample12.navigation.LoginNavGraph
//import com.example.cdsample12.Admin.AdminScaffold
import com.example.cdsample12.ui.theme.CDSample12Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CDSample12Theme {
                // A surface container using the 'background' color from the theme
                RootNavigationGraph(navController = rememberNavController())
            }


        }
    }
}

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = RooteGraph.Route.route,
        startDestination = RooteGraph.LoginNavGraph.route
    ) {
        LoginNavGraph(navController = navController)
        composable(route = RooteGraph.MainScreen.route) {
            MainScreen()
        }
    }
}


//
//@Composable
//fun LoginApplication(navController: NavHostController) {
////    val navController = rememberNavController()
//    val navBarNavController = rememberNavController()
//    val navBarNavControllerAdmin = rememberNavController()
//
//    NavHost(
//        route = ScreenPage.Route.route,
//        navController = navController,
//        startDestination = ScreenPage.LoginScreenPage.route
//    ) {
//        composable(
//            route = ScreenPage.LoginScreenPage.route,
//            content = {
//                LoginScreen(
//                    navController = navController,
//                )
//            })
//        composable(
//            route = ScreenPage.RegisterScreenPage.route,
//            content = { RegisterScreen(navController = navController) })
//        composable(
//            route = ScreenPage.AppScaffold.route,
//            content = {
//                AppScaffold(navController = navBarNavController) {
//                    navController.navigate(ScreenPage.LoginScreenPage.route)
//                }
//            })
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    CDSample12Theme {
//        Greeting("Android")
//    }
//}