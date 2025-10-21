package pt.iade.ei.waycareapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.navigation.AppNavHost
import pt.iade.ei.waycareapp.ui.theme.WayCareTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WayCareTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}
