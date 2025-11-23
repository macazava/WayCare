package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Fotografia(
    @SerializedName("id")
    val foto_id: Long? = null,

    @SerializedName("nome")
    val foto_nome: String? = null,

    @SerializedName("url")
    val foto_url: String? = null,

    @SerializedName("caminho")
    val foto_caminho: String? = null,

    @SerializedName("mime")
    val foto_mime: String? = null,

    @SerializedName("tamanho")
    val foto_tamanho: Long? = null,

    @SerializedName("reporte")
    val foto_rep_id: Long? = null
)

