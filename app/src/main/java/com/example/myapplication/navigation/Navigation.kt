package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.data.AppState
import com.example.myapplication.ui.views.LoginScreen

@Composable
fun AppNavigation(navHostController: NavHostController, appState: AppState){
    NavHost(
        navHostController = navHostController, startDestination = "login",
    ){

        composable("login"){LoginScreen( navController = navHostController)}
    }
}