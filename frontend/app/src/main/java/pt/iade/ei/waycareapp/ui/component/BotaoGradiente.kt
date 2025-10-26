package pt.iade.ei.waycareapp.ui.component

import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotaoGradiente(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    altura: Dp = 48.dp,
    corTexto: Color = Color.White,
    tamanhoTexto: Int = 16,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(altura)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF3F51B5), Color(0xFFE91E63))
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
        ) {
        Text(
            text = texto,
            fontSize = tamanhoTexto.sp,
            fontWeight = FontWeight.Medium,
            color = corTexto
        )
    }
}


