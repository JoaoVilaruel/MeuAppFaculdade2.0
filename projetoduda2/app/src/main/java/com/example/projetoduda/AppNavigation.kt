package com.example.projetoduda.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projetoduda.*

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("home") { TelaHome(navController) }
        composable("area_aluno") { TelaCursos(navController) }
        composable("notas") { TelaNotas() }
        composable("perfil") { TelaPerfil(navController) }
        composable("ajuda") { TelaAjuda() }
        composable("financeiro") { TelaFinanceiro(navController) }
        composable("splash"){ TelaSplash(navController) }
        composable("outros") { TelaAjuda() }
        composable("editarPerfil") { TelaEditarPerfil(navController) }

    }
}
