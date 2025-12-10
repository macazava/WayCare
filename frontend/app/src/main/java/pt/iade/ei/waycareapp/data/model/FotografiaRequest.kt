package pt.iade.ei.waycareapp.data.model

data class FotografiaRequest(
    val nome: String,
    val caminho: String? = null,
    val mime: String,
    val tamanho: Long,
    val descricao: String? = null,
    val url: String,
    val reporteId: Long,
    val anomaliaId: Long? = null,
    val utilizadorId: Long? = null
)

//.