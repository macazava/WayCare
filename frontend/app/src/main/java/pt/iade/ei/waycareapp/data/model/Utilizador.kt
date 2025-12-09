package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Utilizador(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("nome")
    val nome: String? = null,

    @SerializedName("email")
    val email: String? = null,

    @SerializedName("password")
    val password: String? = null
)



