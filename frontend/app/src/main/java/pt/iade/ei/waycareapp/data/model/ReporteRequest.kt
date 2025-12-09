package pt.iade.ei.waycareapp.data.model

data class ReporteRequest(
    val utilizadorId: Long,
    val anomaliaId: Long,
    val localizacaoId: Long,
    val descricao: String,
    val fotoUrl: String?,
    val tipoPersonalizado: String?,
    val zona: String,
    val grauPerigo: String
)
