package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class NotificacaoRepository {
    public interface notificacaoRepository extends JpaRepository<Notificacao, Long> {
        List<Notificacao> findByUtilizador_Id(Long utilizadorId);
    }
}
