package pt.iade.ei.waycareapp.ui.screens.reporte

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.util.Log
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import pt.iade.ei.waycareapp.data.model.*
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import pt.iade.ei.waycareapp.ui.viewmodel.AuthUiState
import pt.iade.ei.waycareapp.ui.viewmodel.LoginViewModel
import pt.iade.ei.waycareapp.viewmodel.ReporteViewModel
import java.time.LocalDateTime
import pt.iade.ei.waycareapp.data.session.SessionManager

@Composable
fun ReportScreen(navController: NavController) {
    var tipoAnomalia by remember { mutableStateOf("") }
    var prioridade by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var detalhesLocalizacao by remember { mutableStateOf("") }
    var imagemUri by remember { mutableStateOf<Uri?>(null) }
    var imagemBitmap by remember { mutableStateOf<Bitmap?>(null) }

    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun obterLocalizacaoAtual(onReady: (Double, Double) -> Unit) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                onReady(location.latitude, location.longitude)
            } else {
                val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000)
                    .setWaitForAccurateLocation(true)
                    .setMaxUpdates(1)
                    .build()

                val callback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        val loc = result.lastLocation
                        if (loc != null) {
                            onReady(loc.latitude, loc.longitude)
                        }
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                }
                fusedLocationClient.requestLocationUpdates(request, callback, context.mainLooper)
            }
        }
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        imagemBitmap = bitmap
    }

    val galeriaLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        imagemUri = uri
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
                    shape = RoundedCornerShape(13.dp)
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
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
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

                imagemBitmap?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Imagem capturada",
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

        var mostrarDialog by remember { mutableStateOf(false) }
        if (mostrarDialog) {
            AlertDialog(
                onDismissRequest = { mostrarDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarDialog = false
                            navController.navigate("home")
                        }
                    ) {
                        Text(
                            text = "Voltar ao início",
                            color = Color(0xFF000000),
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                title = {
                    Text(
                        text = "Reporte enviado com sucesso!",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF3F51B5)
                    )
                },
                text = {
                    Text(
                        text = "Obrigado por contribuir para a melhoria da acessibilidade urbana.",
                        fontSize = 16.sp,
                        color = Color(0xFF444444)
                    )
                },
                containerColor = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(16.dp)
            )
        }

        val reporteViewModel: ReporteViewModel = viewModel()
        val loginViewModel: LoginViewModel = viewModel()
        val authState by loginViewModel.authState.collectAsState()

        BotaoGradiente(
            texto = "Enviar Reporte",
            onClick = {
                Log.d("Botao", "Clique no botão detectado ✅")

                // Ir buscar diretamente o utilizador autenticado à sessão
                val utilizadorLogado = SessionManager.utilizadorLogado

                if (utilizadorLogado != null) {
                    Log.d("Botao", "Utilizador autenticado: ${utilizadorLogado.uti_id} - ${utilizadorLogado.uti_email}")

                    obterLocalizacaoAtual { lat, lng ->
                        Log.d("Localizacao", "Lat: $lat, Lng: $lng")

                        val reporte = Reporte(
                            rep_id = 0, // backend gera
                            rep_uti_id = utilizadorLogado, // ✅ objeto completo
                            rep_ano_id = Anomalia(
                                ano_id = 0, // backend gera
                                tip_id = TipoAnomalia(
                                    tip_id = 0, // backend gera
                                    tip_nome = tipoAnomalia
                                ),
                                ano_descricao = descricao,
                                ano_grau_perigo = prioridade
                            ),
                            rep_tipo_personalizado = if (tipoAnomalia == "Outro") descricao else "",
                            rep_loc_id = Localizacao(
                                loc_id = 0, // backend gera
                                loc_latitude = lat,
                                loc_longitude = lng,
                                loc_endereco = detalhesLocalizacao
                            ),
                            fotografia = Fotografia(
                                foto_id = 0, // backend gera
                                foto_nome = "imagem.jpg",
                                foto_rep_id = 0, // backend gera
                                foto_url = imagemUri?.toString() ?: "",
                                foto_caminho = "",
                                foto_mime = "image/jpeg",
                                foto_tamanho = 0
                            ),
                            rep_estado = "Pendente",
                            rep_data = LocalDateTime.now().toString(),
                            rep_descricao = descricao
                        )

                        Log.d("Reporte", "Objeto preparado para envio: $reporte")

                        reporteViewModel.enviarReporte(reporte)
                        mostrarDialog = true
                    }
                } else {
                    Log.e("Reporte", "❌ Utilizador não autenticado — não é possível enviar reporte")
                }
            }
        )

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
                    contentDescription = "Abrir menu",
                    modifier = Modifier.clickable { expanded = true }
                )
            },
            modifier = Modifier.fillMaxWidth()
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
    val navController = rememberNavController()
    ReportScreen(navController = navController)
}

