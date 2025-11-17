package com.example.waycare.Service;

import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Anomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnomaliaService {

    @Autowired
    private AnomaliaRepository anomaliaRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    public List<Anomalia> listarTodos() {
        return anomaliaRepository.findAll();
    }

    public Optional<Anomalia> procurarPorId(Long id) {
        return anomaliaRepository.findById(id);
    }

    public Anomalia criar(Anomalia anomalia) {
        return anomaliaRepository.save(anomalia);
    }

    public Anomalia atualizar(Long id, Anomalia anomalia) {
        Anomalia existente = anomaliaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obstáculo não encontrado"));
        String novoEstado = new String();
        anomalia.setEstado(novoEstado);
        anomaliaRepository.save(anomalia);

        // Enviar notificação para utilizadores próximos
        if (anomalia.getLocalizacao() != null) {
            String mensagem = "Nova anomalia perto de si: " + anomalia.getDescricao();
            String tipo = "Alerta";
            double raioKm = 5.0;

            notificacaoService.criarNotificacaoParaProximos(
                    anomalia.getLocalizacao(),
                    mensagem,
                    tipo,
                    raioKm
            );
        }

        return anomalia;
    }

    public void eliminar(Long id) {
        anomaliaRepository.deleteById(id);
    }

    public Anomalia atualizarEstado(Long id, String novoEstado) {
        return null;
    }
}

