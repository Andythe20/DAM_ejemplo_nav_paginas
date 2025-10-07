package com.example.myapplication.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class Usuario(
    val email: String,
    val password: String
)

class AppState(private val dataStore: DataStoreManager) {
    val usuarios = mutableStateListOf<Usuario>()
    var usuarioActual: Usuario? = null
    val notasPorUsuario = mutableStateMapOf<String, SnapshotStateList<String>>()

    private val scope = CoroutineScope(Dispatchers.IO)

    //carga de datos inciales
    fun cargarDatos(){
        scope.launch{
            val users = dataStore.getUsers().first()
            val notes = dataStore.getNotes().first()

            usuarios.clear()
            usuarios.addAll(users)

            notasPorUsuario.clear()
            notes.forEach { (k, v) ->
                notasPorUsuario[k] = v.toMutableStateList()
            }
        }
    }

    //Registro de usuario
    fun registrarUsuario(email: String, password: String): Boolean{
        if(usuarios.any { it.email == email }) return false
        val nuevoUsuario = Usuario(email, password)
        usuarios.add(nuevoUsuario)
        return true
    }

    fun agregarNotas(nota: String){
        val email = usuarioActual?.email ?: return
        val notas = notasPorUsuario.getOrPut(email){ mutableStateListOf() }
        notas.add(nota)
        guardarNotas()
    }

    //guardar en DataStore el usuario
    private fun guardarUsuario(){
        scope.launch {
            dataStore.saveUsers(usuarios)
        }

    }

    private fun guardarNotas(){
        scope.launch {
            dataStore.saveNotes(notasPorUsuario)
        }
    }

    //login
    fun login(email: String, password: String): Boolean{
        val user = usuarios.find { it.email == email && it.password == password }
        usuarioActual = user
        return user != null
    }

    //logout
    fun logout(){ usuarioActual = null }

    //obtener notas
    fun getNotas(): List<String>{
        val email = usuarioActual?.email ?: return emptyList()
        return notasPorUsuario[email] ?: mutableStateListOf()
    }

    //borrar notas de a una (de usuario logeado)
    fun borrarNota(index: Int){
        val email = usuarioActual?.email ?: return
        notasPorUsuario[email] ?.let {
            if (index in it.indices){
                it.removeAt(index)
                guardarNotas()
            }
        }
    }



}