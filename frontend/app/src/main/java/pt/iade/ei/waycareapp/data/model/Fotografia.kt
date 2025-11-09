package pt.iade.ei.waycareapp.data.model

data class Fotografia(
    val foto_id: Long,
    val foto_nome: String,
    val foto_rep_id: Long,
    val foto_url: String,
    val foto_caminho: String,
    val foto_mime: String,
    val foto_tamanho: Long
)
