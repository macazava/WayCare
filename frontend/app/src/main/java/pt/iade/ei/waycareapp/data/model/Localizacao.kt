package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName

data class Localizacao(
    @SerializedName("id")
    val loc_id: Long? = null,

    @SerializedName("latitude")
    val loc_latitude: Double,

    @SerializedName("longitude")
    val loc_longitude: Double,

    @SerializedName("endereco")
    val loc_endereco: String
)
