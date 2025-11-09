package pt.iade.ei.waycareapp.data.model

import java.time.LocalDate


data class Reporte(
    val rep_id: Long,
    val rep_uti_id: Utilizador,
    val rep_ano_id: Anomalia,
    val rep_tipo_personalizado: String,
    val rep_loc_id: Localizacao,
    val fotografia: Fotografia,
    val rep_estado: String,
    val rep_data: String = LocalDate.now().toString(),
    val rep_descricao: String
)
