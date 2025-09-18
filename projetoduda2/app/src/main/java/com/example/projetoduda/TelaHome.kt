package com.example.projetoduda

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.example.projetoduda.components.BottomBar
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun TelaHome(navController: NavHostController) {
    var texto by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomBar(navController, "home") },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = Dp(10F)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContentHome(texto, onTextoChange = { texto = it }, navController)
            Spacer(modifier = Modifier.height(10.dp))
            CategoryRows(navController)

        }



    }
}

@Composable
fun ContentHome(
    texto: String,
    onTextoChange: (String) -> Unit,
    navController: NavHostController
) {
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        UserHeader()

        bloco3(
            titulo = "Análise e Desenvolvimento de Sistemas",
            color = Color(0xFFE3F2FD),
            altura = 125,
            largura = 400,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
        )

        bloco(
            titulo = "Ambiente Virtual",
            color = Color(0xFFFAFAFA),
            altura = 90,
            largura = 400,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            onClick = {
                Toast.makeText(context, "Ambiente Virtual clicado", Toast.LENGTH_SHORT).show()
            }
        )

        bloco(
            titulo = "Área do Aluno",
            color = Color(0xFFFAFAFA),
            altura = 90,
            largura = 400,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
            onClick = { navController.navigate("area_aluno") }
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text("Central de Ajuda!", style = MaterialTheme.typography.bodyLarge)
        OutlinedTextField(
            value = texto,
            onValueChange = onTextoChange,
            label = { Text("Procurar no App...", style = MaterialTheme.typography.bodySmall) },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )
    }
}

@Composable
fun UserHeader() {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            color = Color(0xFFBBDEFB),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Ícone de rosto",
                modifier = Modifier.size(30.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text("Olá, Usuário :)", style = MaterialTheme.typography.headlineSmall)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CategoryRows(navController: NavHostController) {
    val items = listOf(
        Triple("Disciplinas", Icons.Default.Warning, "disciplinas"),
        Triple("Documentos", Icons.Default.Email, "documentos"),
        Triple("Financeiro", Icons.Default.ShoppingCart, "financeiro"),
        Triple("Bolsas", Icons.Default.Star, "bolsas"),
        Triple("Formatura", Icons.Default.DateRange, "formatura"),
        Triple("Acessos", Icons.Default.AccountBox, "acessos"),
        Triple("Matrícula", Icons.Default.Settings, "matricula"),
        Triple("Outros Serviços", Icons.Default.Info, "outros")
    )

    for (i in items.chunked(2)) {
        Row(horizontalArrangement = Arrangement.Center) {
            i.forEach { (title, icon, route) ->
                bloco2(
                    titulo = title,
                    color = Color.White,
                    altura = 70,
                    largura = 200,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    imageVector = icon,
                    onClick = {
                        navController.navigate(route)
                    }
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
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
            IconButton(onClick = {
                navController.navigate("home")
            }) {
                Icon(Icons.Default.Home, contentDescription = "Home")
            }
            IconButton(onClick = {
                Toast.makeText(context, "Área do Aluno", Toast.LENGTH_SHORT).show()
                navController.navigate("AreaAluno")
            }) {
                Icon(Icons.Default.Create, contentDescription = "Curso")
            }
            IconButton(onClick = {
                Toast.makeText(context, "Carrinho clicado", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrinho")
            }
            IconButton(onClick = {
                Toast.makeText(context, "Perfil clicado", Toast.LENGTH_SHORT).show()
                navController.navigate("perfil")
            }) {
                Icon(Icons.Default.Person, contentDescription = "Perfil")
            }
        }
    }
}


@Composable
fun bloco(
    titulo: String,
    color: Color,
    altura: Int,
    largura: Int,
    textAlign: TextAlign,
    style: TextStyle,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(largura.dp)
            .height(altura.dp)
            .padding(5.dp)
            .clickable(onClick = onClick),
        color = color,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                style = style,
                color = Color.Black,
                textAlign = textAlign,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Avançar",
                modifier = Modifier.size(24.dp),
                tint = Color.DarkGray
            )
        }
    }
}

@Composable
fun bloco2(
    titulo: String,
    color: Color,
    altura: Int,
    largura: Int,
    textAlign: TextAlign,
    style: TextStyle,
    imageVector: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .width(largura.dp)
            .height(altura.dp)
            .padding(5.dp)
            .clickable(onClick = onClick),
        color = color,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null,
                modifier = Modifier.size(28.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = titulo,
                style = style,
                color = Color.Black,
                textAlign = textAlign,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun bloco3(
    titulo: String,
    color: Color,
    altura: Int,
    largura: Int,
    textAlign: TextAlign,
    style: TextStyle,
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .width(largura.dp)
            .height(altura.dp)
            .padding(5.dp)
            .clickable(onClick = onClick),
        color = color,
        shadowElevation = 4.dp,
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = titulo,
                style = style,
                color = Color.Black,
                textAlign = textAlign
            )
            Column {
                Text("RGM: 123456789", style = MaterialTheme.typography.bodyMedium)
                Text("Cursando", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
@Composable
fun TelaAjuda() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Central de Suporte", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Aqui você encontrará suporte e ajuda para utilizar o aplicativo.")
    }
}