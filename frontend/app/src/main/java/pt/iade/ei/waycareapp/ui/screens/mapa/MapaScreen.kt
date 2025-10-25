package pt.iade.ei.waycareapp.ui.screens.mapa

import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun MapaScreen(context: Context) {
    // Configura o OSMDroid
    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", Context.MODE_PRIVATE))

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val mapView = MapView(it)
            mapView.setTileSource(TileSourceFactory.MAPNIK)
            mapView.setMultiTouchControls(true)

            // Posição inicial (Montijo/Lisboa)
            val startPoint = GeoPoint(38.7069, -9.1450)
            mapView.controller.setZoom(15.0)
            mapView.controller.setCenter(startPoint)

            // Exemplo de marcador
            val marker = Marker(mapView)
            marker.position = GeoPoint(38.7075, -9.1440)
            marker.title = "Passeio bloqueado por obras"
            mapView.overlays.add(marker)

            mapView
        }
    )
}
