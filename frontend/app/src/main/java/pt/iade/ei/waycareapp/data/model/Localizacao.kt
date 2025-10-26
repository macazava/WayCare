package pt.iade.ei.waycareapp.data.model

data class Localizacao(
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val endereco: String
)