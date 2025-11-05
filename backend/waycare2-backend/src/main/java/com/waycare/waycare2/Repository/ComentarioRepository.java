package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Notificacao;

import java.util.List;

public interface ComentarioRepository {
    List<ComentarioRepository> findByReporte_Id(Long reporteId);


    List<Notificacao.Comentario> findByUtilizador_Id(Long utilizadorId);

    Notificacao.Comentario save(Notificacao.Comentario comentario);

    List<Notificacao.Comentario> findAll();
}
