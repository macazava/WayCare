package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Utilizador(

    @SerializedName("id")
    val uti_id: Long,

    @SerializedName("nome")
    val uti_nome: String,

    @SerializedName("email")
    val uti_email: String,

    @SerializedName("password")
    val uti_password: String,
)
