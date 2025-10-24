package pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreenSuccess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreen

@Composable
fun ReportSuccessScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ícone de sucesso
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Sucesso",
            tint = Color(0xFF4CAF50),
            modifier = Modifier.size(64.dp)
        )

        // Mensagem de sucesso
        Text(
            text = "O seu reporte foi enviado com sucesso!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // Detalhes do reporte
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("📝 Passeio bloqueado por Obras", fontSize = 16.sp)
            Text("⚠️ Prioridade: Alta", fontSize = 16.sp)
            Text("📍 Localização: Rua da Liberdade, 123 Lisboa", fontSize = 16.sp)
            Text("🔖 Referência: #REP-2025-0142", fontSize = 16.sp)
            Text("🕒 Enviado a 25/03/2025 - 11:08am", fontSize = 14.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botão para voltar ao início
        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
        ) {
            Text("Voltar ao Início", color = Color.White)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReportSuccessPreview() {
    ReportSuccessScreen(navController = rememberNavController())
}


