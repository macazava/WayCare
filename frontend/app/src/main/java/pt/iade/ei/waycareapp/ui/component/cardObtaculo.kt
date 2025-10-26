package pt.iade.ei.waycareapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.waycareapp.data.model.Categoria
import pt.iade.ei.waycareapp.data.model.Localizacao
import pt.iade.ei.waycareapp.data.model.Obstaculo
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.Utilizador

@Composable
fun CardObstaculo(reporte: Reporte, onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEDAFF)),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            // Título principal
            Text(
                text = reporte.obstaculo.categoria.nome,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Localização
            Text(
                text = reporte.localizacao.endereco,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF555555)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descrição detalhada
            Text("Descrição:", fontWeight = FontWeight.SemiBold)
            Text(
                text = reporte.comentario,
                fontSize = 15.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Reportado por
            Text(
                text = "Reportado por: ${reporte.utilizador.nome} - ${reporte.utilizador.id} reporte(s)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3F51B5)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botão de ação
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF3F51B5), Color(0xFFE91E63))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                TextButton(onClick = onClose) {
                    Text(
                        text = "Voltar ao Mapa",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardObstaculoPreview() {
    val mockReporte = Reporte(
        id = 1,
        utilizador = Utilizador(1, "Maria", "maria@email.com", "1234", "912345678"),
        obstaculo = Obstaculo(
            id = 1,
            categoria = Categoria(1, "Passeio Bloqueado por Obras", "Obras na via"),
            descricao = "Passeio totalmente obstruído",
            grauPerigo = "Alto"
        ),
        localizacao = Localizacao(1, 38.7169, -9.1399, "Rua da Liberdade 123, Lisboa"),
        data = java.time.LocalDateTime.now(),
        estado = "Pendente",
        comentario = "Espaço insuficiente para cadeiras de rodas"
    )

    CardObstaculo(reporte = mockReporte, onClose = {})
}
