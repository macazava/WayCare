package pt.iade.ei.waycareapp.data.model

data class Utilizador(
    val id: Long,
    val nome: String,
    val email: String,
    val password: String,
    val telefone: String
)
