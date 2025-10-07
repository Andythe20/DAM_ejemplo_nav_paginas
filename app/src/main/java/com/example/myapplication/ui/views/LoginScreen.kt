package com.example.myapplication.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.data.AppState

/*Guardar componentes para reutilizarlos*/

/*
navcontroller: para moverme entre pantallas.
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, appState: AppState) {

    /* variables para guardar el usuario, la contraseña y el error.
    * by remember: para que se mantenga el estado de la variable
    * mutableStateOf: para que se pueda modificar el estado de la variable*/
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }

    /*Definir scaffold*/
    Scaffold (
        //topBar = { TopAppBar(title = { Text("Login") }) }
        topBar = { TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = { Text("Login")},
            //añadir padding solo arriba
            modifier = Modifier
                .padding(top = 20.dp),

        )}

    ) {padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center

        ){
            OutlinedTextField(
                value = usuario,
                onValueChange = {usuario = it},
                label = {Text("Usuario")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            /*la constraseña no debe mostrarse en la pantalla*/
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Contraseña")},
                modifier = Modifier.fillMaxWidth()
            )

            /*error a corregir*/
            Spacer(Modifier.height(16.dp))

            if (error.isNotEmpty()){
                Text(error, color = MaterialTheme.colorScheme.error)
                Spacer(Modifier.height(8.dp))
            }

            /*validaciones de campos (hacer en una clase aparte)
            posible corrutina para validar los campos*/
            Button(
                onClick = {
                    if (usuario.isBlank() || password.isBlank()){
                        error = "Usuario y/o contraseña vacíos."
                    } else if (appState.login(usuario, password)){
                        error = ""
                        navController.navigate("NotasScreen")
                    } else {
                        error = "Usuario y/o contraseña incorrectos."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            TextButton(onClick = {navController.navigate("RegisterScreen")}) {
                Text("No tienes una cuenta? Registrate")
            }
        }
    }

}

