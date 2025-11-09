package pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavController) {
    var receiveEmailAlerts by remember { mutableStateOf(false) }
    var proximityNotifications by remember { mutableStateOf(true) }
    var locationAccess by remember { mutableStateOf(true) }
    var userType by remember { mutableStateOf("mobilidade") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Topo com gradiente e título
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Perfil",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }

        // Dados do utilizador
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.LightGray, shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            Text("Maria Carvalho", fontSize = 20.sp)
            Text("@maria123", fontSize = 16.sp, color = Color.Gray)
            Text("Tipo de utilizador: Pessoa com mobilidade reduzida", fontSize = 14.sp, color = Color.DarkGray)

            Button(
                onClick = { navController.navigate("myReports") },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text("4 Reportes", color = Color.White)
            }
        }
        // Opções de perfil
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Mudar Nome, E-mail ou Password",
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ir para edição",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* vai a uma pagina que deixa editar essas informações */ },
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(1.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Idioma da App",
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Ir para edição",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* escolher as opções de idioma (pode ser espanhol, ingles, alemão, etc...) */ },
                    tint = Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Receber alertas por Email",
                    fontSize = 16.sp
                )
                Switch(checked = receiveEmailAlerts, onCheckedChange = { receiveEmailAlerts = it })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Receber Notificações de Proximidade",
                    fontSize = 16.sp
                )
                Switch(checked = proximityNotifications, onCheckedChange = { proximityNotifications = it })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Permitir Acesso à sua Localização",
                    fontSize = 16.sp
                )
                Switch(checked = locationAccess, onCheckedChange = { locationAccess = it })
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                    Text("Tipo de Utilizador:", fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Pessoa com Mobilidade Reduzida",
                    fontSize = 16.sp,
                )
                RadioButton(
                    selected = userType == "mobilidade",
                    onClick = { userType = "mobilidade" }
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "Apagar conta",
                color = Color.Red,
                modifier = Modifier.clickable { /* ação de apagar conta */ }
            )
        }
      }
    }


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    val fakeNavController = rememberNavController()
    ProfileScreen(navController = fakeNavController)
}