package com.example.projetoduda

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.projetoduda.ui.theme.ProjetodudaTheme


@Composable
fun TelaCursos(navController: NavHostController) {
    var texto by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { com.example.projetoduda.components.BottomBar(navController, "area_aluno") },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ContentCursos(texto, onTextoChange = { texto = it }, navController)
        }
    }
}
@Composable
fun ContentCursos(texto: String, onTextoChange: (String) -> Unit, navController: NavHostController) {
    val context = LocalContext.current

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            NewHeader()

            Row {
                Column {
                    Row {
                        bloco(
                            altura = 300,
                            largura = 180,
                            color = Color(0xFFE3F2FD),
                            texto = "Horários de Aulas",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge,
                            onClick = {
                                Toast.makeText(context, "Horários ainda não disponíveis", Toast.LENGTH_SHORT).show()
                            }
                        )
                        Column {
                            bloco(
                                altura = 150,
                                largura = 180,
                                color = Color(0xFFFAFAFA),
                                texto = "Notas",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                onClick = {
                                    navController.navigate("notas")
                                }
                            )
                            bloco(
                                altura = 150,
                                largura = 180,
                                color = Color(0xFFFAFAFA),
                                texto = "N\nFaltas",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleLarge,
                                onClick = {
                                    Toast.makeText(context, "Faltas: 0%", Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                    bloco(
                        altura = 120,
                        largura = 360,
                        color = Color(0xFFD0F8CE),
                        texto = "100%",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.headlineSmall,
                        onClick = {
                            Toast.makeText(context, "Parabéns pelo desempenho!", Toast.LENGTH_SHORT).show()
                        }
                    )
                    bloco(
                        altura = 60,
                        largura = 360,
                        color = Color.White,
                        texto = "Temas transversais",
                        textAlign = TextAlign.Left,
                        style = MaterialTheme.typography.bodyMedium,
                        onClick = {
                            Toast.makeText(context, "Em breve...", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            Spacer(modifier = Modifier.height(12.dp))
            Text("Meu Curso", style = MaterialTheme.typography.titleLarge)
            CategoryRows1()
        }
    }
}

@Composable
fun NewHeader() {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier.size(45.dp),
            color = Color(0xFFFFF59D),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Ícone de estrela",
                modifier = Modifier
                    .size(24.dp)
                    .padding(10.dp),
                tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text("Análise e Desenvolvimento de Sistemas", style = MaterialTheme.typography.titleMedium)
            Text("RGM: 123456789", style = MaterialTheme.typography.bodySmall)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun bloco(
    texto: String,
    color: Color,
    altura: Int,
    largura: Int,
    textAlign: TextAlign,
    style: TextStyle,
    imageVector: ImageVector? = null,
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
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (imageVector != null) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp),
                    tint = Color.Black
                )
            }
            Text(
                text = texto,
                style = style,
                color = Color.Black,
                textAlign = textAlign,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CategoryRows1() {
    val context = LocalContext.current
    val items = listOf(
        Pair("Histórico Escolar", Icons.Default.Warning),
        Pair("Emissão de Documentos", Icons.Default.Email),
        Pair("Meus Documentos", Icons.Default.MailOutline),
    )

    for (i in items.chunked(2)) {
        Row {
            i.forEach { (title, icon) ->
                bloco(
                    texto = title,
                    color = Color(0xFFFFFFFF),
                    altura = 70,
                    largura = 175,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                    imageVector = icon,
                    onClick = {
                        Toast.makeText(context, "$title clicado", Toast.LENGTH_SHORT).show()
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun TelaNotas() {
    val notas = listOf(
        Nota("Matemática", 9.0),
        Nota("Lógica de Programação", 8.5),
        Nota("POO", 10.0),
        Nota("Banco de Dados", 9.2)
    )

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Notas do Aluno",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(notas.size) { index ->
            val nota = notas[index]
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFE0F7FA),
                shape = RoundedCornerShape(8.dp),
                tonalElevation = 2.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(nota.disciplina, style = MaterialTheme.typography.bodyLarge)
                    Text(nota.nota.toString(), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}

data class Nota(val disciplina: String, val nota: Double)