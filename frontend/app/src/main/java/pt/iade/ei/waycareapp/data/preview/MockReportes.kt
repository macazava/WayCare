package pt.iade.ei.waycareapp.data.preview

import pt.iade.ei.waycareapp.data.model.Categoria
import pt.iade.ei.waycareapp.data.model.Localizacao
import pt.iade.ei.waycareapp.data.model.Obstaculo
import pt.iade.ei.waycareapp.data.model.Reporte
import pt.iade.ei.waycareapp.data.model.Utilizador
import java.time.LocalDateTime

val mockReportes = listOf(
    Reporte(
        id = 1,
        utilizador = Utilizador(1, "Maria", "maria@email.com", "1234", "912345678"),
        obstaculo = Obstaculo(
            id = 1,
            categoria = Categoria(1, "Rampa Inexistente", "Não há rampa de acesso"),
            descricao = "Sem rampa na entrada",
            grauPerigo = "Médio"
        ),
        localizacao = Localizacao(1, 38.717, -9.139, "Rua A"),
        data = LocalDateTime.now(),
        estado = "Pendente",
        comentario = "Muito difícil para cadeiras de rodas"
    ),
    Reporte(
        id = 2,
        utilizador = Utilizador(2, "João", "joao@email.com", "1234", "912123456"),
        obstaculo = Obstaculo(
            id = 2,
            categoria = Categoria(1, "Rampa Inexistente", "rampa inexistente"),
            descricao = "Sem rampa na entrada",
            grauPerigo = "Médio"
        ),
        localizacao = Localizacao(1, 38.717, -9.139, "Rua A"),
        data = LocalDateTime.now(),
        estado = "Pendente",
        comentario = "Muito difícil para cadeiras de rodas"
    )
)
