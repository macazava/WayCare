package pt.iade.ei.waycareapp.data.model

data class ReporteRequest(
    val utilizadorId: Long,
    val tipoId: Long,
    val descricao: String,
    val fotoUrl: String?,
    val tipoPersonalizado: String?,
    val zona: Zona,
    val grauPerigo: GrauPerigo,
    val latitude: Double,
    val longitude: Double,
    val endereco: String? = null
)