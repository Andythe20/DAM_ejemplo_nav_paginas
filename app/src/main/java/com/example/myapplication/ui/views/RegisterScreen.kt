package com.example.myapplication.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.AppState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController, appState: AppState) {

    Scaffold (
        topBar = { TopAppBar(title = { Text("Registro Usuario") }) }
    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ){
            //var usuario by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            //var error by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Email")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Contraseña")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(16.dp))

            //faltan validaciones
            Button(
                onClick = {navController.navigate("LoginScreen")},
                modifier = Modifier.fillMaxWidth()
            ) { Text("Registrarse")}

        }
    }
}