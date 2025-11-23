package pt.iade.ei.waycareapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.Utilizador
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance
import pt.iade.ei.waycareapp.data.session.SessionManager

// Estados possíveis da autenticação
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class LoginSuccess(val user: Utilizador) : AuthUiState()   // ← agora guarda o utilizador
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
                val user = Utilizador(
                    uti_email = email,
                    uti_password = password
                )

                val response = RetrofitInstance.authApi.login(user)

                if (response.isSuccessful && response.body() != null) {
                    val utilizadorLogado = response.body()!!
                    Log.d("API", "Login ok: $utilizadorLogado")

                    SessionManager.utilizadorLogado = utilizadorLogado
                    _authState.value = AuthUiState.LoginSuccess(utilizadorLogado)
                } else {
                    _authState.value = AuthUiState.Error("Erro login: ${response.code()}")
                }
            } catch (e: Exception) {
                _authState.value = AuthUiState.Error("Falha login: ${e.message}")
            }
        }
    }

    // Função de registo
    fun register(nome: String, email: String, password: String) {
        _authState.value = AuthUiState.Loading
        viewModelScope.launch {
            try {
                val novoUser = Utilizador(
                    uti_nome = nome,
                    uti_email = email,
                    uti_password = password
                )

                val response = RetrofitInstance.authApi.register(novoUser)

                if (response.isSuccessful && response.body() != null) {
                    val utilizadorCriado = response.body()!!
                    Log.d("API", "Registo ok: $utilizadorCriado")
                    _authState.value = AuthUiState.RegisterSuccess(utilizadorCriado)
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

