package com.example.waycare.Service;

import com.example.waycare.Repository.ComentarioRepository;
import com.example.waycare.models.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    public Comentario criarComentario(Comentario comentario) {
        Comentario novo = comentarioRepository.save(comentario);

        if (novo.getReporte() != null && novo.getReporte().getLocalizacao() != null) {
            notificacaoService.criarNotificacaoParaProximos(
                    novo.getReporte().getLocalizacao(),
                    "O reporte '" + novo.getReporte().getDescricao() + "' recebeu um novo comentário.",
                    "Atualização",
                    3.0
            );
        }

        return novo;
    }

}
