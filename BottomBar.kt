package com.example.projetoduda.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BottomBar(navController: NavHostController, currentRoute: String) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        color = Color(0xFFE0E0E0),
        shadowElevation = 6.dp,
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(Icons.Default.Home, contentDescription = "Home",
                    tint = if (currentRoute == "home") Color.Blue else Color.Black)
            }
            IconButton(onClick = { navController.navigate("area_aluno") }) {
                Icon(Icons.Default.Create, contentDescription = "Área do Aluno",
                    tint = if (currentRoute == "area_aluno") Color.Blue else Color.Black)
            }
            IconButton(onClick = { navController.navigate("financeiro") }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Financeiro",
                    tint = if (currentRoute == "financeiro") Color.Blue else Color.Black)
            }
            IconButton(onClick = { navController.navigate("perfil") }) {
                Icon(Icons.Default.Person, contentDescription = "Perfil",
                    tint = if (currentRoute == "perfil") Color.Blue else Color.Black)
            }


        }
    }
}
