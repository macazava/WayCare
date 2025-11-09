package pt.iade.ei.waycareapp.data.model

data class Localizacao(
    val loc_id: Long,
    val loc_latitude: Double,
    val loc_longitude: Double,
    val loc_endereco: String
)