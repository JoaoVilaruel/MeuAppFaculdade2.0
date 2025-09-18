package com.example.projetoduda

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun TelaPerfil(navController: NavHostController) {
    val context = LocalContext.current


    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val nomeEditado = savedStateHandle?.getStateFlow("nome", "Usuário")?.collectAsState()
    val emailEditado = savedStateHandle?.getStateFlow("email", "Digite seu Email")?.collectAsState()
    val telefoneEditado = savedStateHandle?.getStateFlow("telefone", "Digite seu número")?.collectAsState()

    Scaffold(
        bottomBar = { com.example.projetoduda.components.BottomBar(navController, "perfil") },
        containerColor = Color(0xFFF9F9F9)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(100.dp),
                color = Color(0xFFB3E5FC),
                shape = CircleShape,
                shadowElevation = 4.dp
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier.padding(20.dp).fillMaxSize(),
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Perfil do Usuário", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(24.dp))

            PerfilField("Nome", nomeEditado?.value ?: "Usuário", false) {}
            PerfilField("Email", emailEditado?.value ?: "Digite seu Email", false) {}
            PerfilField("Telefone", telefoneEditado?.value ?: "Digite seu número", false, KeyboardType.Phone) {}

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("editarPerfil") },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81D4FA))
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Editar")
            }
        }
    }
}

@Composable
fun PerfilField(
    label: String,
    value: String,
    editable: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        enabled = editable,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}
