package com.example.projetoduda

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projetoduda.components.BottomBar


data class Financeiro(
    val titulo: String,
    val valor: Double,
    var status: String
)

@Composable
fun TelaFinanceiro(navController: NavHostController) {
    val context = LocalContext.current


    var itens by remember {
        mutableStateOf(
            listOf(
                Financeiro("Mensalidade Fevereiro/2025", 750.0, "PAGO"),
                Financeiro("Mensalidade Março/2025", 750.0, "PENDENTE"),
                Financeiro("Taxa de Biblioteca", 50.0, "PENDENTE")
            )
        )
    }


    var itemSelecionado by remember { mutableStateOf<Financeiro?>(null) }
    var mostrarDialog by remember { mutableStateOf(false) }


    val totalPago = itens.filter { it.status == "PAGO" }.sumOf { it.valor }
    val totalPendente = itens.filter { it.status == "PENDENTE" }.sumOf { it.valor }

    Scaffold(
        bottomBar = { BottomBar(navController, "financeiro") },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                "Financeiro",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            FinanceiroResumo(totalPago, totalPendente)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(itens.size) { index ->
                    val item = itens[index]
                    FinanceiroItem(
                        item = item,
                        onPagar = {
                            itemSelecionado = item
                            mostrarDialog = true
                        }
                    )
                }
            }
        }

        if (mostrarDialog && itemSelecionado != null) {
            AlertDialog(
                onDismissRequest = { mostrarDialog = false },
                title = { Text("Forma de Pagamento") },
                text = {
                    Column {
                        Text("Escolha como deseja pagar:")
                        Spacer(Modifier.height(12.dp))
                        Button(
                            onClick = {
                                itens = itens.map {
                                    if (it == itemSelecionado) it.copy(status = "PAGO") else it
                                }
                                Toast.makeText(context, "Pago via Boleto!", Toast.LENGTH_SHORT).show()
                                mostrarDialog = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Boleto")
                        }
                        Spacer(Modifier.height(8.dp))
                        Button(
                            onClick = {
                                itens = itens.map {
                                    if (it == itemSelecionado) it.copy(status = "PAGO") else it
                                }
                                Toast.makeText(context, "Pago via PIX!", Toast.LENGTH_SHORT).show()
                                mostrarDialog = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                        ) {
                            Text("PIX")
                        }
                    }
                },
                confirmButton = {}
            )
        }
    }
}

@Composable
fun FinanceiroResumo(totalPago: Double, totalPendente: Double) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        color = Color(0xFFE3F2FD),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Resumo Financeiro", style = MaterialTheme.typography.titleMedium)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Pagos", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "R$ %.2f".format(totalPago),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Green
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Pendentes", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "R$ %.2f".format(totalPendente),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun FinanceiroItem(item: Financeiro, onPagar: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        color = Color.White,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(item.titulo, style = MaterialTheme.typography.bodyLarge)
                Text("R$ %.2f".format(item.valor), style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }

            if (item.status == "PAGO") {
                Text("PAGO", color = Color.Green, style = MaterialTheme.typography.bodyMedium)
            } else {
                Button(
                    onClick = onPagar,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF001E))
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                    Spacer(Modifier.width(4.dp))
                    Text("Pagar")
                }
            }
        }
    }
}
