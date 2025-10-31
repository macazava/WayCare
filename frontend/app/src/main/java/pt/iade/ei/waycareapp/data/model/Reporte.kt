package pt.iade.ei.waycareapp.data.model

data class Reporte(
    val id: Long,
    val utilizador: Utilizador,
    val obstaculo: Obstaculo,
    val localizacao: Localizacao,
    val data: String,
    val estado: String,
    val comentario: String
)