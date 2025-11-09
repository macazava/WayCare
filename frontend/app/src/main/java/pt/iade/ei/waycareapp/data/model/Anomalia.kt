package pt.iade.ei.waycareapp.data.model

data class Anomalia(
    val ano_id: Long,
    val tip_id: TipoAnomalia,
    val ano_descricao: String,
    val ano_grau_perigo: String
)