package pt.iade.ei.waycareapp.data.models

data class Reporte(
    val id: Int,
    val tipo: String, // Ex: "Incidente", "Serviço", "Rampa Inexistente"
    val prioridade: String, // Ex: "Alta", "Média", "Baixa"
    val latitude: Double,
    val longitude: Double,
    val descricao: String
)