package pt.iade.ei.waycareapp.ui.screens.mapa

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@SuppressLint("MissingPermission")
@Composable
fun MapaScreen() {
    val context = LocalContext.current

    // Configuração inicial do OSMDroid
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val mapView = MapView(it)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setMultiTouchControls(true)

            // Ponto inicial: Lisboa
            val startPoint = GeoPoint(38.7169, -9.1399)
            mapView.controller.setZoom(15.0)
            mapView.controller.setCenter(startPoint)

            // Marcador
            val marker = Marker(mapView)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = "Estás aqui"
            mapView.overlays.add(marker)

            mapView
        }
    )
}


/*criar um Preview simulado
criar uma versão visual falsa da MapaScreen,
só para mostrar no Preview, sem o mapa real.
Assim se mantem a consistência visual e da para testar layout.*/
@Composable
fun MapaScreenPreviewFake() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Mapa aqui (simulação)",
            color = Color.Gray
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MapaScreenPreview() {
    MapaScreenPreviewFake()
}



