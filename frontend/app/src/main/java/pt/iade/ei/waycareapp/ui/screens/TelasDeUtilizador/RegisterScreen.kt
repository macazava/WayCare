package pt.iade.ei.waycareapp.ui.screens.TelasDeUtilizador

import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import pt.iade.ei.waycareapp.R
import pt.iade.ei.waycareapp.ui.component.BotaoGradiente
import pt.iade.ei.waycareapp.ui.viewmodel.LoginViewModel
import pt.iade.ei.waycareapp.ui.viewmodel.AuthUiState

@Composable
fun RegisterScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    var nome by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") } // não usado no backend, mas mantive
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isReducedMobility by remember { mutableStateOf(false) }

    val authState by viewModel.authState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        //arrowback para login screen
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
                    .clickable { navController.navigate("login") }
                    .size(32.dp),
                tint = Color(0xFF2196F3)
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.FillWidth
            )
        }
        Text(
            text = "Faça o seu registo!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF7A2CC),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start)
        )
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Digite o seu Nome") },
            placeholder = { Text("Maria Carvalho") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Digite o seu E-mail") },
            placeholder = { Text("Maria123@gmail.com") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Escreva a sua Palavra-passe") },
            placeholder = { Text("••••••••") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = "Mostrar/Esconder")
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "É um utilizador com Mobilidade Reduzida?",
                fontSize = 15.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isReducedMobility,
                onCheckedChange = { isReducedMobility = it }
            )
        }

        // Botão de registo com gradiente
        BotaoGradiente(
            texto = "Registar-me",
            onClick = {
                viewModel.register(nome, email, password) // ✅ chama o backend
            }
        )

        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Ou registe-se com", color = Color.Gray)
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Image(
                    painter = painterResource(id = pt.iade.ei.waycareapp.R.drawable.ic_google),
                    contentDescription = "Google",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp),
                )
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 1.dp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Já tem uma conta? ")
            Text(
                text = "Faça Login agora",
                color = Color.Blue,
                modifier = Modifier.clickable {
                    navController.navigate("login")
                }
            )
        }

        // ✅ Observa o estado da autenticação
        when (authState) {
            is AuthUiState.Loading -> {
                CircularProgressIndicator()
            }
            is AuthUiState.RegisterSuccess -> {
                LaunchedEffect(Unit) {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    val fakeNavController = rememberNavController()
    RegisterScreen(navController = fakeNavController)
}
