package com.example.myapplication.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.AppState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotasScreen(navController: NavHostController, appState: AppState) {
    Scaffold (
        topBar = { TopAppBar(title = { Text("Block de Notas") }) }
    ){ padding ->
        var nota by remember { mutableStateOf("") }
        val notaList = remember { mutableStateListOf<String>() }

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding((padding))
                .padding(16.dp)
        ){
            OutlinedTextField(
                value = nota,
                onValueChange = { nota = it },
                label = { Text("Escribe una nota")},
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    if (nota.isNotBlank()){
                        notaList.add(nota)
                        nota = ""
                    }
                }) {
                Text("Guardar Nota")
            }
            Spacer(Modifier.height(16.dp))

            Text("Notas Guardadas:")
            notaList.forEach { n ->
                Text("● $n")
            }
        }
    }
}

/*
Scaffold(
topBar = { TopAppBar(title = { Text("Bienvenido a tus notas") }) }
) { padding ->
    var nota by remember { mutableStateOf("") }
    val notaList = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = nota,
            onValueChange = { nota = it },
            label = { Text("Nota") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        Button(
            onClick = {
                if (nota.isNotBlank()) {
                    notaList.add(nota)
                    nota = ""
                }

            }
        ) {Text("Guardar nota") }
        Spacer(Modifier.height(16.dp))

        Text("Tus notas guardadas:")
        notaList.forEach {
                nota -> Text("* $nota")
        }
    }
}
*/
