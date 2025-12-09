package com.example.waycare.Service;

import com.example.waycare.Repository.NotificacaoRepository;
import com.example.waycare.models.Notificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
    public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    public List<Notificacao> listarTodas() {
        return notificacaoRepository.findAll();
    }

    public List<Notificacao> listarPorUtilizador(Long utiId) {
        return notificacaoRepository.findByUtilizadorId(utiId);
    }

    public Notificacao criar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);
    }

    public void marcarComoLida(Long id) {
        Notificacao n = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        n.setLida(true);
        notificacaoRepository.save(n);
    }
}
