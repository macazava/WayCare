package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class FotografiaRequest(
    @SerializedName("nome")
    val nome: String,

    @SerializedName("caminho")
    val caminho: String? = null,

    @SerializedName("mime")
    val mime: String,

    @SerializedName("tamanho")
    val tamanho: Long,

    @SerializedName("descricao")
    val descricao: String? = null,

    @SerializedName("url")
    val url: String, // aqui vai o link final do Cloudinary

    @SerializedName("reporte")
    val reporteId: Long
)
