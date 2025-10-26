package pt.iade.ei.waycareapp.ui.screens.reporte.ReportScreenSuccess

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente

@Composable
fun ReportScreenSuccess(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                Spacer(modifier = Modifier.width(8.dp))
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "WayCare",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF8F8F8)
                    )
                    Text(
                        text = "Detalhes do Reporte",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "O seu reporte foi",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5) // azul escuro
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "enviado com sucesso!",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5) // azul escuro
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0x40464EB0),
                            Color(0x40D9226A)
                        )
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Passeio bloqueado por Obras", fontSize = 16.sp, color = Color.DarkGray, fontWeight = FontWeight.Bold)
            Text("- Prioridade: Alta", fontSize = 16.sp, color = Color.DarkGray)
            Text("- Localização: Rua da Liberdade, 123 Lisboa", fontSize = 16.sp, color = Color.DarkGray)
            Text("- Referência: #REP-2025–0142", fontSize = 16.sp, color = Color.DarkGray)
            Text("Enviado a 25/9/2025 - 10h50", fontSize = 14.sp, color = Color.Gray)
        }


        Spacer(modifier = Modifier.height(70.dp))

        BotaoGradiente(
            texto = "Voltar ao início",
            onClick = { navController.navigate("home") }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReportScreenSuccessPreview() {
    ReportScreenSuccess(navController = rememberNavController())
}



