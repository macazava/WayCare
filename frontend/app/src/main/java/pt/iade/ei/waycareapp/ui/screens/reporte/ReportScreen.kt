package pt.iade.ei.waycareapp.ui.screens.reporte

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.*
import pt.iade.ei.waycareapp.data.model.*
import pt.iade.ei.waycareapp.data.session.SessionManager
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import pt.iade.ei.waycareapp.utils.CloudinaryHelper
import pt.iade.ei.waycareapp.utils.Utils
import pt.iade.ei.waycareapp.viewmodel.ReporteViewModel
import java.util.Locale
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun ReportScreen(navController: NavController, reporteViewModel: ReporteViewModel = viewModel()) {


    var tipoAnomalia by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var detalhesLocalizacao by remember { mutableStateOf(Zona.LISBOA.name) }
    var prioridade by remember { mutableStateOf(GrauPerigo.BAIXA.name) }
    var imagemUri by remember { mutableStateOf<Uri?>(null) }
    var imagemBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var tipoSelecionado by remember { mutableStateOf<TipoAnomalia?>(null) }

    val context = LocalContext.current
    val tiposBackend by reporteViewModel.tiposAnomalia.collectAsState()

    LaunchedEffect(Unit) {
        reporteViewModel.carregarTiposAnomalia()
        CloudinaryHelper.init(context) //inicializa a cloudinary
    }

    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    @SuppressLint("MissingPermission")
    fun obterLocalizacaoAtual(onReady: (Double, Double, String) -> Unit) {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000)
            .setWaitForAccurateLocation(true)
            .setMaxUpdates(1)
            .build()
        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation
                if (loc != null) {
                    val geocoder = Geocoder(context, Locale.getDefault())
                    val addresses = geocoder.getFromLocation(loc.latitude, loc.longitude, 1)
                    val endereco = if (!addresses.isNullOrEmpty()) addresses[0].getAddressLine(0) else "Endereço não disponível"
                    onReady(loc.latitude, loc.longitude, endereco)
                }
                fusedLocationClient.removeLocationUpdates(this)
            }
        }
        fusedLocationClient.requestLocationUpdates(request, callback, context.mainLooper)
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        imagemBitmap = bitmap
        bitmap?.let { tempBitmap ->
            val uri = Utils.bitmapToUri(context, tempBitmap)
            imagemUri = uri
            uri?.let { reporteViewModel.uploadFoto(it, context) } // envia para Cloudinary
        }
    }


    val galeriaLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        imagemUri = uri
        uri?.let { reporteViewModel.uploadFoto(it, context) }
    }

    val prioridades = GrauPerigo.values().map { it.name }
    val zonas = Zona.values().map { it.name }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
    //header
        Box(
            modifier = Modifier.fillMaxWidth().height(80.dp).background(
                brush = Brush.horizontalGradient(colors = listOf(Color(0xFF3F51B5), Color(0xFFE91E63))),
                shape = RoundedCornerShape(13.dp)
            ),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Voltar", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("WayCare", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    Text("Reportar Anomalia", fontSize = 20.sp, color = Color.White)
                }
            }
        }

        DropdownField(
            label = "Selecione o Tipo de Anomalia",
            options = tiposBackend.map { it.tip_nome },
            selected = tipoSelecionado?.tip_nome ?: tipoAnomalia,
            onSelect = { nome ->
                tipoAnomalia = nome
                tipoSelecionado = tiposBackend.find { it.tip_nome == nome }
            }
        )

        DropdownField("Selecione o Grau de Perigo", prioridades, prioridade) { prioridade = it }

        OutlinedTextField(
            value = descricao,
            onValueChange = { if (it.length <= 1000) descricao = it },
            label = { Text("Descreva o Problema") },
            modifier = Modifier.fillMaxWidth().height(100.dp),
            maxLines = 6
        )

        Box(
            modifier = Modifier.fillMaxWidth().height(180.dp).background(
                brush = Brush.horizontalGradient(colors = listOf(Color(0x663F51B5), Color(0x66E91E63))),
                shape = RoundedCornerShape(12.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "camera",
                        modifier = Modifier.size(32.dp).clickable { cameraLauncher.launch(null) })
                    Icon(Icons.Default.PhotoLibrary, contentDescription = "galeria",
                        modifier = Modifier.size(32.dp).clickable { galeriaLauncher.launch("image/*") })
                }
                imagemBitmap?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(bitmap = it.asImageBitmap(), contentDescription = "", modifier = Modifier.size(100.dp))
                }
            }
        }

        Text("A localização é obtida automaticamente.", fontSize = 16.sp)
        DropdownField("Escolha a zona", zonas, detalhesLocalizacao) { detalhesLocalizacao = it }

        BotaoGradiente(
            texto = "Enviar Reporte",
            onClick = {
                val userId = SessionManager.utilizadorLogado?.id ?: 0
                if (tipoSelecionado == null) {
                    Toast.makeText(context, "Selecione o tipo", Toast.LENGTH_SHORT).show()
                    return@BotaoGradiente
                }

                obterLocalizacaoAtual { lat, lng, endereco ->
                    val grauEnum = GrauPerigo.valueOf(prioridade)
                    val zonaEnum = Zona.valueOf(detalhesLocalizacao)

                    val request = ReporteRequest(
                        utilizadorId = userId,
                        tipoId = tipoSelecionado!!.tip_id!!,
                        descricao = descricao,
                        fotoUrl = reporteViewModel.fotoUrl.value,
                        tipoPersonalizado = null,
                        zona = zonaEnum,
                        grauPerigo = grauEnum,
                        latitude = lat,
                        longitude = lng,
                        endereco = endereco
                    )

                    Log.d("Reporte", "Enviando reporte: $request")
                    reporteViewModel.enviarReporte(request)

                    reporteViewModel.fotoUrl.value?.let { url ->

                    }

                    Toast.makeText(context, "Reporte enviado!", Toast.LENGTH_LONG).show()
                    navController.navigate("home")
                }
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
            trailingIcon = {
                Icon(Icons.Default.ArrowDropDown, contentDescription = "", modifier = Modifier.clickable { expanded = !expanded })
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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
