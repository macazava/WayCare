package com.example.waycare.Service;

import com.example.waycare.Repository.NotificacaoRepository;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.models.Localizacao;
import com.example.waycare.models.Notificacao;
import com.example.waycare.models.Utilizador;
import org.antlr.v4.runtime.tree.pattern.ParseTreePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void enviarNotificacao(Notificacao notificacao) {
        notificacaoRepository.save(notificacao);
        messagingTemplate.convertAndSend("/notificacoes/fluxo", notificacao);
    }

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    // Criar notificação para todos os utilizadores próximos de uma localização
    public void criarNotificacaoParaProximos(Localizacao localizacao, String mensagem, String tipo, double raioKm) {
        List<Utilizador> todos = utilizadorRepository.findAll();

        for (Utilizador u : todos) {
            if (u.getLocalizacao() != null) {
                double distancia = calcularDistancia(localizacao, u.getLocalizacao());
                if (distancia <= raioKm) {
                    Notificacao notif = new Notificacao();
                    notif.setMensagem(mensagem);
                    notif.setTipo(tipo);
                    notif.setUtilizador(u);
                    notif.setDataEnvio(LocalDateTime.now());
                    notif.setLida(false);
                    notificacaoRepository.save(notif);
                }
            }
        }
    }
    // Método auxiliar para calcular distância entre duas localizações (Haversine)
    private double calcularDistancia(Localizacao loc1, Localizacao loc2) {
        final int R = 6371;
        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<Notificacao> listarPorUtilizador(Long utilizadorId) {
        return notificacaoRepository.findByUtilizadorId(utilizadorId);
    }

    public Notificacao criar(Notificacao notificacao) {
        return notificacaoRepository.save(notificacao);

    }

    public Notificacao marcarComoLida(Long id) {
        Notificacao notif = notificacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notif.setLida(true);
        return notificacaoRepository.save(notif);
    }

    public void eliminar(Long id) {
        notificacaoRepository.deleteById(id);



    }

}
