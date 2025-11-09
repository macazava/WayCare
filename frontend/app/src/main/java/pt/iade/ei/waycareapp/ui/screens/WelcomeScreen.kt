package pt.iade.ei.waycareapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun WelcomeScreen(navController: NavController) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF37799), Color(0xFF2196F3))
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Text(
                text = "WayCare",
                fontWeight = FontWeight.Bold,
                fontSize = 55.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Bem-vindo!",
                fontSize = 25.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { navController.navigate("login") },
                shape = RoundedCornerShape(30),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFE97293)
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Avançar",
                    tint = Color(0xFFE57090)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Comece agora",
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "Welcome Preview")
@Composable
fun WelcomeScreenPreview() {
    pt.iade.ei.waycareapp.ui.theme.WayCareTheme {
        WelcomeScreen(navController = rememberNavController())
    }
}

