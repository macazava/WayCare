package pt.iade.ei.waycareapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import pt.iade.ei.waycareapp.screens.LoginScreen
import pt.iade.ei.waycareapp.screens.WelcomeScreen
import pt.iade.ei.waycareapp.screens.HomeScreen
import pt.iade.ei.waycareapp.screens.RegisterScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        // Depois adicionar mais rotas aqui: login, home, reportar, mapa, etc.
    }
}

