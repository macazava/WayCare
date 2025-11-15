package pt.iade.ei.waycareapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pt.iade.ei.waycareapp.data.model.Utilizador
import pt.iade.ei.waycareapp.data.remote.RetrofitInstance

// Estados possíveis da autenticação
sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    data class LoginSuccess(val message: String) : AuthUiState()
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
                val user = Utilizador(uti_id = 0, uti_nome = "", uti_email = email, uti_password = password)
                val response = RetrofitInstance.authApi.login(user)
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthUiState.LoginSuccess(response.body()!!)
                    Log.d("API", "Login ok: ${response.body()}")
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
                val newUser = Utilizador(uti_id = 0, uti_nome = nome, uti_email = email, uti_password = password)
                val response = RetrofitInstance.authApi.register(newUser)
                if (response.isSuccessful && response.body() != null) {
                    _authState.value = AuthUiState.RegisterSuccess(response.body()!!)
                    Log.d("API", "Registo ok: ${response.body()}")
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


