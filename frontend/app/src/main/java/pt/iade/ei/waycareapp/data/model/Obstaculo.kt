package pt.iade.ei.waycareapp.data.model

data class Obstaculo(
    val id: Long,
    val categoria: Categoria,
    val descricao: String,
    val grauPerigo: String
)