package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.data.AppState
import com.example.myapplication.data.DataStoreManager
import com.example.myapplication.navigation.AppNavigation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStore = DataStoreManager(applicationContext)
        val appState = AppState(dataStore)

        enableEdgeToEdge()
        setContent {
            MyApp(appState)
        }
    }
}

@Composable
fun MyApp(appState: AppState) {
    val navController = rememberNavController()
    MaterialTheme{
        AppNavigation(navController, appState)
    }

//primera pagina para inciar la aplicacion
//    NavHost(navController = navController, startDestination = "LoginScreen"){
//        //definir rutas
//        composable("LoginScreen") { LoginScreen(navController) }
//        composable("RegisterScreen") { RegistroScreen(navController) }
//        composable("NotasScreen") { NotasScreen(navController) }
//    }

}
