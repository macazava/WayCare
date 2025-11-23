package pt.iade.ei.waycareapp.data.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate


data class Reporte(
    @SerializedName("id")
    val rep_id: Long,

    @SerializedName("utilizador")
    val rep_uti_id: Utilizador,

    @SerializedName("anomalia")
    val rep_ano_id: Anomalia?,

    @SerializedName("tipoPersonalizado")
    val rep_tipo_personalizado: String?,

    @SerializedName("localizacao")
    val rep_loc_id: Localizacao?,

    @SerializedName("fotografias")
    val fotografias: List<Fotografia> = emptyList(),

    @SerializedName("estado")
    val rep_estado: String = "Pendente",

    @SerializedName("data")
    val rep_data: String = LocalDate.now().toString(),

    @SerializedName("descricao")
    val rep_descricao: String
)
