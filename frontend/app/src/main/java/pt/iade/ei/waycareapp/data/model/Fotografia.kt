package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Fotografia(
    @SerializedName("id")
    val foto_id: Long,

    @SerializedName("nome")
    val foto_nome: String,

    @SerializedName("url")
    val foto_url: String,

    @SerializedName("caminho")
    val foto_caminho: String,

    @SerializedName("mime")
    val foto_mime: String,

    @SerializedName("tamanho")
    val foto_tamanho: Long,

    @SerializedName("reporte")
    val foto_rep_id: Long
)

