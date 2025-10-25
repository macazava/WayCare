package pt.iade.ei.waycareapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.waycareapp.screens.WelcomeScreen
import pt.iade.ei.waycareapp.screens.HomeScreen
import pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador.LoginScreen
import pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador.ProfileScreen
import pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador.RegisterScreen
import pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreen
import pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreenSuccess.ReportScreenSuccess
import pt.iade.ei.waycareapp.MainActivity
import pt.iade.ei.waycareapp.ui.screens.mapa.MapaScreen


@Composable
fun AppNavHost(navController: NavHostController, activity: MainActivity) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController = navController, activity = activity ) }
        composable("register") { RegisterScreen(navController) }
        composable("profile") { ProfileScreen (navController) }
        composable("report") { ReportScreen(navController) }
        composable("reportSuccess"){ ReportScreenSuccess(navController) }
        composable("mapView"){ MapaScreen() }
        // Depois adicionar mais rotas aqui: login, home, reportar, mapa, etc.
    }
}



