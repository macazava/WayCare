package com.example.waycare.Repository;

import com.example.waycare.models.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
    List<Notificacao> findByUtilizadorId(Long utilizadorId);
}
