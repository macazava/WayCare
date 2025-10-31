package pt.iade.ei.waycareapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.navigation.AppNavHost
import pt.iade.ei.waycareapp.ui.theme.WayCareTheme
import pt.iade.ei.waycareapp.ui.viewmodel.ReporteViewModel

class MainActivity : ComponentActivity() {
    val reporteViewModel = viewModel<ReporteViewModel>()
    var devePedirPermissao = false
    fun pedirPermissaoSeNecessario() {
        if (devePedirPermissao) {
            val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )

            val missing = permissions.filter {
                ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
            }

            if (missing.isNotEmpty()) {
                ActivityCompat.requestPermissions(this, missing.toTypedArray(), 1001)
            }

            devePedirPermissao = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        devePedirPermissao = true
        pedirPermissaoSeNecessario()

        setContent {
            WayCareTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, activity = this)
            }
        }
    }

}

