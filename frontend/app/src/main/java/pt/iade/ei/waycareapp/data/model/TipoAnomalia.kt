package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class TipoAnomalia(
    @SerializedName("id")
    val tip_id: Long,

    @SerializedName("nome")
    val tip_nome: String
)
