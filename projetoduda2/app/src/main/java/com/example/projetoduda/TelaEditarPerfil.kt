package com.example.projetoduda

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TelaEditarPerfil(navController: NavController) {
    var nome by remember { mutableStateOf("Usuário") }
    var email by remember { mutableStateOf("usuario@email.com") }
    var telefone by remember { mutableStateOf("11999999999") }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Editar Perfil") },
                onBack = { navController.popBackStack() }
            )
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = telefone,
                onValueChange = { telefone = it },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81D4FA)),
                onClick = {
                navController.previousBackStackEntry?.savedStateHandle?.set("nome", nome)
                navController.previousBackStackEntry?.savedStateHandle?.set("email", email)
                navController.previousBackStackEntry?.savedStateHandle?.set("telefone", telefone)
                navController.popBackStack()
            }) {
                Text("Salvar")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopAppBar(
    title: @Composable () -> Unit,
    onBack: (() -> Unit)? = null
) {
    TopAppBar(
        title = { title() },
        navigationIcon = {
            if (onBack != null) {
                IconButton(onClick = { onBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.Black)
                }
            }
        }
    )
}
