package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Anomalia(
    @SerializedName("id")
    val ano_id: Long,

    @SerializedName("tipoAnomalia")
    val tip_id: TipoAnomalia,

    @SerializedName("descricao")
    val ano_descricao: String,

    @SerializedName("grauPerigo")
    val ano_grau_perigo: String
)