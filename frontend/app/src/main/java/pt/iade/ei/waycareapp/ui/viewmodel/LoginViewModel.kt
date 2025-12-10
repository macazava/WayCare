package pt.iade.ei.waycareapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.LoginRequest
import pt.iade.ei.waycareapp.data.model.Utilizador
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import pt.iade.ei.waycareapp.data.session.SessionManager
import com.google.gson.Gson
import pt.iade.ei.waycareapp.data.model.RegisterRequest

// Estados possíveis da autenticação
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class LoginSuccess(val user: Utilizador) : AuthUiState()
    data class RegisterSuccess(val user: Utilizador) : AuthUiState()
    data class Error(val message: String) : AuthUiState()
}

class LoginViewModel : ViewModel() {

    private val _authState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val authState: StateFlow<AuthUiState> = _authState

    // Função de login
    fun login(email: String, password: String) {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch {
            try {
                val request = LoginRequest(email = email, password = password)
                Log.d("Login", "JSON enviado (login): ${Gson().toJson(request)}")

                val response = RetrofitInstance.authApi.login(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        Log.d("API", "Login ok: $body")
                        SessionManager.utilizadorLogado = body
                        _authState.value = AuthUiState.LoginSuccess(body)
                    } else {
                        Log.e("API", "Login: resposta sem corpo")
                        _authState.value = AuthUiState.Error("Resposta sem corpo")
                    }
                } else {
                    val errorMsg = response.errorBody()?.string().orEmpty()
                    Log.e("API", "Erro login: ${response.code()} - $errorMsg")
                    _authState.value = AuthUiState.Error("Erro login: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("API", "Falha login: ${e.message}")
                _authState.value = AuthUiState.Error("Falha login: ${e.message}")
            }
        }
    }

    // Função de registo
    fun register(
        nome: String,
        email: String,
        password: String,
        confirmarPassword: String,
        dataNascimento: String,
        genero: String,
        telemovel: String
    ) {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch {
            try {
                val request = RegisterRequest(
                    nome = nome,
                    email = email,
                    password = password,
                    confirmarPassword = confirmarPassword,
                    dataNascimento = dataNascimento,
                    genero = genero,
                    telemovel = telemovel
                )

                val response = RetrofitInstance.authApi.register(request)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _authState.value = AuthUiState.RegisterSuccess(body)
                    } else {
                        _authState.value = AuthUiState.Error("Resposta sem corpo")
                    }
                } else {
                    _authState.value = AuthUiState.Error("Erro registo: ${response.code()}")
                }
            } catch (e: Exception) {
                _authState.value = AuthUiState.Error("Falha registo: ${e.message}")
            }
        }
    }

    fun reset() {
        _authState.value = AuthUiState.Idle
    }
}
