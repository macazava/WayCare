package pt.iade.ei.waycareapp.ui.screens.reporte

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ReportScreen(navController: NavController) {
    var tipoAnomalia by remember { mutableStateOf("") }
    var prioridade by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var detalhesLocalizacao by remember { mutableStateOf("") }

    val tipos = listOf("Rampas Inexistentes", "Passeios Danificados", "Passadeiras mal Sinalizadas", "Zonas Perigosas", "Buraco na via", "Sinalização danificada", "Outro")
    val prioridades = listOf("Baixa", "Média", "Alta")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Topo com gradiente e título
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF3F51B5), Color(0xFFE91E63))
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "WayCare",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8F8F8),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Text(
                        text = "Reportar Anomalia",
                        fontSize = 20.sp,
                        color = Color.White
                    )

                }

            }
        }

        // Tipo de Anomalia
        DropdownField(
            label = "Selecione o Tipo de Anomalia",
            options = tipos,
            selected = tipoAnomalia,
            onSelect = { tipoAnomalia = it }
        )

        // Prioridade
        DropdownField(
            label = "Selecione a Prioridade",
            options = prioridades,
            selected = prioridade,
            onSelect = { prioridade = it }
        )

        // Descrição
        OutlinedTextField(
            value = descricao,
            onValueChange = {
                if (it.length <= 1000) descricao = it
            },
            label = { Text("Descreva o Problema") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            maxLines = 6
        )

        // Foto da Anomalia
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0x663F51B5), Color(0x66E91E63))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .clickable { /* ação para abrir câmera ou galeria */ },
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Inserir imagem",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Clique aqui para tirar ou carregar uma fotografia",
                    color = Color.DarkGray
                )
            }


        }

        // Localização
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Localização Automática:", fontSize = 16.sp)
            //só um exemplo, aqui temos que retirar as informações da fotografia para utilizar a localização
            Text("Rua da Liberdade, 123, Lisboa, Portugal", color = Color.Gray)
            OutlinedTextField(
                value = detalhesLocalizacao,
                onValueChange = { detalhesLocalizacao = it },
                label = {
                    Text(
                        text = "Detalhes adicionais da Localização",
                        fontSize = 13.sp
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

        }
        Spacer(modifier = Modifier.height(5.dp))
        // Botão Enviar Reporte
        Button(
            onClick = { navController.navigate("reportSuccess") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFFE91E63), Color(0xFF2196F3))
                        ),
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text("Enviar Reporte", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DropdownField(
    label: String,
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Abrir menu"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReportScreenPreview() {
    val fakeNavController = rememberNavController()
    ReportScreen(navController = fakeNavController)
}
