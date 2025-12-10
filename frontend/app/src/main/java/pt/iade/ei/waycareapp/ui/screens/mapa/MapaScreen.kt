package pt.iade.ei.waycareapp.ui.screens.mapa

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.LocationServices
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.ui.component.CardObstaculo
import pt.iade.ei.waycareapp.viewmodel.ReporteViewModel
import androidx.compose.runtime.collectAsState
import android.view.View
import androidx.core.content.ContextCompat


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MissingPermission")
@Composable
fun MapaScreen(navController: NavController) {
    val context = LocalContext.current
    var userLocation by remember { mutableStateOf<GeoPoint?>(null) }
    var reporteSelecionado by remember { mutableStateOf<Reporte?>(null) }
    var filtroSelecionado by remember { mutableStateOf("Mostrar Tudo") }
    val reporteViewModel: ReporteViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val reportes by reporteViewModel.reportes.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        reporteViewModel.carregarReportes()
    }


    LaunchedEffect(Unit) {
        Configuration.getInstance().userAgentValue = context.packageName
        Configuration.getInstance().load(
            context,
            context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE)
        )

        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            location?.let {
                userLocation = GeoPoint(it.latitude, it.longitude)
            } ?: run {
                userLocation = GeoPoint(38.7169, -9.1399)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                MapView(ctx).apply {
                    id = View.generateViewId()
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                }
            },
            update = { map ->

                val startPoint = userLocation ?: GeoPoint(38.7169, -9.1399)

                map.controller.setZoom(15.0)
                map.controller.setCenter(startPoint)

                map.overlays.clear()

                // marcador do utilizador 👤
                val userMarker = Marker(map).apply {
                    position = startPoint
                    title = "📍 Você está aqui"
                }
                map.overlays.add(userMarker)

                // aplicar filtro!!
                val reportesFiltrados = reportes.filter { reporte ->
                    when (filtroSelecionado) {
                        "Mostrar Tudo" -> true
                        "Prioridade Alta" -> reporte.rep_ano_id?.ano_grau_perigo == "Alto"
                        "Prioridade Média" -> reporte.rep_ano_id?.ano_grau_perigo == "Médio"
                        "Prioridade Baixa" -> reporte.rep_ano_id?.ano_grau_perigo == "Baixo"
                        else -> true
                    }
                }

                // desenhar os pins
                reportesFiltrados.forEach { reporte ->
                    val marker = Marker(map).apply {
                        position = GeoPoint(
                            reporte.rep_loc_id?.loc_latitude ?: 0.0,
                            reporte.rep_loc_id?.loc_longitude ?: 0.0
                        )
                        title = reporte.rep_ano_id?.tip_id?.tip_nome ?: "Tipo"
                        subDescription = reporte.rep_descricao ?: ""
                        setOnMarkerClickListener { _, _ ->
                            reporteSelecionado = reporte
                            true
                        }
                    }
                    map.overlays.add(marker)
                }

                map.invalidate()
            }

        )



        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MapaHeader(navController, filtroSelecionado) { novoFiltro ->
                filtroSelecionado = novoFiltro
            }
        }

        reporteSelecionado?.let {
            CardObstaculo(reporte = it, onClose = { reporteSelecionado = null })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapaHeader(navController: NavController, filtroSelecionado: String, onFiltroChange: (String) -> Unit) {
    Spacer(modifier = Modifier.height(15.dp))
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "WayCare",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8F8F8)
                    )
                    Text(
                        text = "Mapa",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }

            FilterDropdown(filtroSelecionado, onFiltroChange)
        }
    }
}

@Composable
fun FilterDropdown(filtroSelecionado: String, onFiltroChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(onClick = { expanded = true }) {
            Icon(
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listOf(
                "Prioridade Baixa",
                "Prioridade Média",
                "Prioridade Alta",
                "Mostrar Tudo",
                "Mostrar Rampas Inexistentes",
                "Mostrar Passeios Danificados",
                "Mostrar passadeiras Mal sinalizadas",
                "Mostrar Zonas Perigosas"
            ).forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onFiltroChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MapaScreenPreview() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        // Substitui o mapa real por uma caixa cinzenta
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text("Mapa (preview)", color = Color.DarkGray)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MapaHeader(navController, filtroSelecionado = "Mostrar Tudo") { }
        }
    }
}