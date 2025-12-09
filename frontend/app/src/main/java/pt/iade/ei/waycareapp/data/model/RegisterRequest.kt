package pt.iade.ei.waycareapp.data.model

data class RegisterRequest(
    val nome: String,
    val email: String,
    val password: String,
    val confirmarPassword: String,
    val dataNascimento: String, // formato "YYYY-MM-DD"
    val genero: String,         // "MASCULINO", "FEMININO" ou "OUTRO"
    val telemovel: String       // 9 dígitos
)
