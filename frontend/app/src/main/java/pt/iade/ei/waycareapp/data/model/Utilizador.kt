package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Utilizador(
    @SerializedName("uti_id")
    val uti_id: Long? = null,

    @SerializedName("uti_nome")
    val uti_nome: String? = null,

    @SerializedName("uti_email")
    val uti_email: String? = null,

    val uti_password: String? = null // só usado no envio, não vem na resposta
)
