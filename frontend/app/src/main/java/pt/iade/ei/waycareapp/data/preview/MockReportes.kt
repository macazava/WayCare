package pt.iade.ei.waycareapp.data.mock

import pt.iade.ei.waycareapp.data.model.*

val mockReportes = listOf(
    Reporte(
        rep_id = 1,
        rep_uti_id = Utilizador(
            uti_id = 101,
            uti_nome = "Maria Silva",
            uti_email = "maria@email.com",
            uti_password = "1234"
        ),
        rep_ano_id = Anomalia(
            ano_id = 201,
            tip_id = TipoAnomalia(
                tip_id = 1,
                tip_nome = "Passeio Danificado"
            ),
            ano_descricao = "Buraco profundo no passeio",
            ano_grau_perigo = "Alto"
        ),
        rep_tipo_personalizado = "Buraco onde dificulta travessia",
        rep_loc_id = Localizacao(
            loc_id = 301,
            loc_latitude = 38.7169,
            loc_longitude = -9.1399,
            loc_endereco = "Rua da Liberdade, Lisboa"
        ),
        fotografia = Fotografia(
            foto_id = 401,
            foto_nome = "buraco.jpg",
            foto_rep_id = 1,// para testar
            foto_url = "https://example.com/buraco.jpg",
            foto_caminho = "/imagens/buraco.jpg",
            foto_mime = "image/jpeg",
            foto_tamanho = 204800
        ),
        rep_estado = "Pendente",
        rep_data = "2025-11-08",
        rep_descricao = "Passeio com buraco perigoso junto à passadeira"
    )
)
