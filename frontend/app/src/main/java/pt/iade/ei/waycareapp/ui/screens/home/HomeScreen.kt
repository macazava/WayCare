package pt.iade.ei.waycareapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import pt.iade.ei.waycareapp.R
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.MainActivity
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreen


@Composable
fun HomeScreen(navController: NavController, activity: MainActivity) {
    LaunchedEffect(Unit) {
        activity.devePedirPermissao = true
        activity.pedirPermissaoSeNecessario()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        // Topo: Logo grande + ícone de perfil azul alinhado verticalmente
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo WayCare",
                modifier = Modifier.size(230.dp)
            )

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFFF8F8F8), shape = CircleShape)
                    .clickable { navController.navigate("profile") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color.Blue,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(55.dp))

        // Botão: Fazer Reporte
        BotaoGradiente(
            texto = "Fazer Reporte",
            onClick = { navController.navigate("report") }
        )

        // Botão: Mapa
        BotaoGradiente(
            texto = "Mapa",
            onClick = { navController.navigate("mapView") }
        )
    }
}



