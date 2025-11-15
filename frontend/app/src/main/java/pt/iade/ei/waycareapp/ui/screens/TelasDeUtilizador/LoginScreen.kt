package pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.R
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import pt.iade.ei.waycareapp.ui.viewmodel.LoginViewModel
import pt.iade.ei.waycareapp.ui.viewmodel.AuthUiState


@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Voltar atrás (ícone)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .clickable { navController.navigate("welcome") }
                    .size(32.dp),
                tint = Color(0xFF2196F3)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Logótipo
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp),
                contentScale = ContentScale.FillWidth
            )
        }

        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Bem-vindo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF7A2CC),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Espaço para o Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            placeholder = { Text("Maria123@gmail.com") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Espaço para a password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Palavra-passe") },
            placeholder = { Text("********") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Mostrar/Esconder")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Esqueceu a sua palavra passe?",
            color = Color(0xFF125FDB),
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { /*pode levar a uma tela para recuperar a palavra passe - ainda não implementado*/ }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão de login com gradiente
        BotaoGradiente(
            texto = "Login",
            onClick = {
                viewModel.login(email, password) // ✅ chama o backend
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text("Não tem uma conta? ")
            Text(
                text = "Faça o registo agora",
                color = Color(0xFF1160DB),
                modifier = Modifier.clickable { navController.navigate("register") }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
        Text("Ou continue com", color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Image(
                painter = painterResource(id = pt.iade.ei.waycareapp.R.drawable.ic_google),
                contentDescription = "Google",
                modifier = Modifier
                    .size(40.dp)
                    .padding(8.dp),
            )
        }

        // Observa o estado da autenticação
        when (authState) {
            is AuthUiState.Loading -> {
                CircularProgressIndicator()
            }
            is AuthUiState.LoginSuccess -> {
                LaunchedEffect(Unit) {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                    viewModel.reset()
                }
            }
            is AuthUiState.Error -> {
                Text(
                    text = (authState as AuthUiState.Error).message,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }
            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    pt.iade.ei.waycareapp.ui.theme.WayCareTheme {
        LoginScreen(navController = rememberNavController())
    }
}
