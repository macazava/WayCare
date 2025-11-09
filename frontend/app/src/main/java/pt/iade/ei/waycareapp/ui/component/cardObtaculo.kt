package pt.iade.ei.waycareapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pt.iade.ei.waycareapp.data.model.TipoAnomalia
import pt.iade.ei.waycareapp.data.model.Localizacao
import pt.iade.ei.waycareapp.data.model.Anomalia
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
                text = reporte.rep_ano_id.tip_id.tip_nome,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Localização
            Text(
                text = reporte.rep_loc_id.loc_endereco,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF555555)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descrição detalhada
            Text("Descrição:", fontWeight = FontWeight.SemiBold)
            Text(
                text = reporte.rep_descricao,
                fontSize = 15.sp,
                color = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Reportado por
            Text(
                text = "Reportado por: ${reporte.rep_uti_id.uti_nome} - ${reporte.rep_uti_id.uti_id} reporte(s)",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF3F51B5)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Botão de ação
            BotaoGradiente(
                texto = "Voltar ao Mapa",
                onClick = onClose
            )
        }
    }
}

