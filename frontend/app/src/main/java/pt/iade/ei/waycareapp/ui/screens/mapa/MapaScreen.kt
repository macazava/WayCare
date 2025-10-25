package pt.iade.ei.waycareapp.ui.screens.mapa

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

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

