package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class ReporteDTO(
    @SerializedName("tipoPersonalizado")
    val tipoPersonalizado: String?,

    @SerializedName("localizacao")
    val localizacao: Localizacao?,

    @SerializedName("fotografias")
    val fotografias: List<Fotografia>?,

    @SerializedName("estado")
    val estado: String,

    @SerializedName("data")
    val data: String,

    @SerializedName("descricao")
    val descricao: String?
)