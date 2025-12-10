package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Reporte(
    @SerializedName("id")
    val rep_id: Long = 0,

    @SerializedName("utilizadorId")
    val rep_uti_id: Long? = null,

    @SerializedName("anomaliaId")
    val rep_ano_id: Long? = null,

    @SerializedName("latitude")
    val rep_latitude: Double? = null,

    @SerializedName("longitude")
    val rep_longitude: Double? = null,

    @SerializedName("descricao")
    val rep_descricao: String,

    @SerializedName("grauPerigo")
    val rep_grau_perigo: String? = null,

    @SerializedName("nomeAnomalia")
    val rep_nome_anomalia: String? = null
)


