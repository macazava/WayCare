package pt.iade.ei.waycareapp.ui.component


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.screens.WelcomeScreen

@Composable
fun CardObstaculo(reporte: Reporte, onClose: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Reportado por: ${reporte.utilizador.nome}", style = MaterialTheme.typography.titleMedium)
            Text("Categoria: ${reporte.obstaculo.categoria.nome}")
            Text("Descrição: ${reporte.obstaculo.descricao}")
            Text("Grau de perigo: ${reporte.obstaculo.grauPerigo}")
            Text("Comentário: ${reporte.comentario}")
            Text("Estado: ${reporte.estado}")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onClose) {
                Text("Fechar")
            }
        }
    }
}

