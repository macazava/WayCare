package pt.iade.ei.waycareapp.ui.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var erro by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)
    var loginSucesso by mutableStateOf(false)

    fun validarLogin(): Boolean {
        if (email.isBlank() || password.isBlank()) {
            erro = "Preenche todos os campos"
            return false
        }
        if (!email.contains("@")) {
            erro = "Email inválido"
            return false
        }
        return true
    }

    fun fazerLogin() {
        if (!validarLogin()) return

        isLoading = true
        erro = null

        viewModelScope.launch {
            delay(1000) // simula chamada ao backend
            isLoading = false
            loginSucesso = true // aqui simulas sucesso
        }
    }
}

