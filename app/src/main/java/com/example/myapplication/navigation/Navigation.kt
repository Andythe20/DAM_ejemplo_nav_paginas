package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.AppState
import com.example.myapplication.ui.views.LoginScreen
import com.example.myapplication.ui.views.NotasScreen
import com.example.myapplication.ui.views.RegistroScreen

@Composable
fun AppNavigation(navController: NavHostController, appState: AppState){
    NavHost(
        navController = navController,
        startDestination = "login",
    ){

        composable("login"){LoginScreen(navController, appState)}
        composable("RegisterScreen"){RegistroScreen(navController, appState)}
        composable("NotasScreen"){NotasScreen(navController, appState)}
    }
}