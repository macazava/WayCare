package pt.iade.ei.waycareapp.ui.screens.reporte

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pt.iade.ei.waycareapp.data.model.*
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import java.time.LocalDateTime

@Composable
fun ReportScreen(navController: NavController) {
    var tipoAnomalia by remember { mutableStateOf("") }
    var prioridade by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var detalhesLocalizacao by remember { mutableStateOf("") }
    var imagemUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val galeriaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imagemUri = uri
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        imagemUri = bitmap?.let {
            val drawable = BitmapDrawable(context.resources, it)
            drawable.toBitmap().let { bmp ->
                Uri.EMPTY // substitui por lógica de gravação se quiseres guardar
            }
        }
    }

    val tipos = listOf("Rampas Inexistentes", "Passeios Danificados", "Passadeiras mal Sinalizadas", "Zonas Perigosas", "Buraco na via", "Sinalização danificada", "Outro")
    val prioridades = listOf("Baixa", "Média", "Alta")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Topo
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
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("WayCare", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF8F8F8))
                    Text("Reportar Anomalia", fontSize = 20.sp, color = Color.White)
                }
            }
        }

        // Tipo e Prioridade
        DropdownField("Selecione o Tipo de Anomalia", tipos, tipoAnomalia) { tipoAnomalia = it }
        DropdownField("Selecione a Prioridade", prioridades, prioridade) { prioridade = it }

        // Descrição
        OutlinedTextField(
            value = descricao,
            onValueChange = { if (it.length <= 1000) descricao = it },
            label = { Text("Descreva o Problema") },
            modifier = Modifier.fillMaxWidth().height(100.dp),
            maxLines = 6
        )

        // Foto da Anomalia
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0x663F51B5), Color(0x66E91E63))
                    ),
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    Icon(
                        imageVector = Icons.Default.CameraAlt,
                        contentDescription = "Abrir câmara",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { cameraLauncher.launch(null) }
                    )
                    Icon(
                        imageVector = Icons.Default.PhotoLibrary,
                        contentDescription = "Abrir galeria",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(32.dp)
                            .clickable { galeriaLauncher.launch("image/*") }
                    )
                }
                Text("Tirar foto ou escolher da galeria", color = Color.DarkGray)

                imagemUri?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(context).data(it).crossfade(true).build(),
                        contentDescription = "Imagem selecionada",
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        // Localização
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Localização Automática:", fontSize = 16.sp)
            Text("Rua da Liberdade, 123, Lisboa, Portugal", color = Color.Gray)
            OutlinedTextField(
                value = detalhesLocalizacao,
                onValueChange = { detalhesLocalizacao = it },
                label = { Text("Detalhes adicionais da Localização", fontSize = 13.sp) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Botão Enviar Reporte
        BotaoGradiente(
            texto = "Enviar Reporte",
            onClick = {
                val reporte = Reporte(
                    id = 142,
                    utilizador = Utilizador(1, "Maria", "maria@email.com", "1234", "912345678"),
                    obstaculo = Obstaculo(
                        id = 1,
                        categoria = Categoria(1, tipoAnomalia, "Descrição automática"),
                        descricao = descricao,
                        grauPerigo = prioridade
                    ),
                    localizacao = Localizacao(
                        id = 1,
                        latitude = 38.7169,
                        longitude = -9.1399,
                        endereco = "Rua da Liberdade, 123 Lisboa"
                    ),
                    data = LocalDateTime.now(),
                    estado = "Pendente",
                    comentario = detalhesLocalizacao
                )

                navController.currentBackStackEntry?.savedStateHandle?.set("reporte", reporte)
                navController.navigate("reportSuccess")
            }
        )
    }
}

@Composable
fun DropdownField(label: String, options: List<String>, selected: String, onSelect: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selected,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            trailingIcon = { Icon(Icons.Default.ArrowDropDown, contentDescription = "Abrir menu") },
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.matchParentSize().clickable { expanded = true })

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }, modifier = Modifier.fillMaxWidth()) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = {
                    onSelect(option)
                    expanded = false
                })
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
