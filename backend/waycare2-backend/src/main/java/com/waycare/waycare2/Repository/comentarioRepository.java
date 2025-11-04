package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Notificacao;

import java.util.List;

public interface comentarioRepository {
    List<comentarioRepository> findByReporte_Id(Long reporteId);

    // Buscar todos os comentários feitos por um utilizador específico
    List<Notificacao.Comentario> findByUtilizador_Id(Long utilizadorId);

    Notificacao.Comentario save(Notificacao.Comentario comentario);
}
