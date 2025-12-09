package com.example.waycare.Controller.websockets;

import com.example.waycare.Service.ProximidadeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ProximidadeWebSocketController {

    @Autowired
    private ProximidadeService proximidadeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/localizacao")
    @SendTo("/topic/alertas")
    public AlertaResponse verificarProximidade(LocalizacaoRequest localizacao) {
        List<ProximidadeService.AnomaliaProxima> anomalias = proximidadeService.encontrarAnomaliasPorPerto(
                localizacao.getLatitude(),
                localizacao.getLongitude()
        );

        return new AlertaResponse(
                anomalias.isEmpty() ? "Nenhum obstáculo por perto" : "ATENÇÃO! Obstáculos detectados!",
                anomalias.size(),
                anomalias
        );
    }


    public void enviarAlertaParaUtilizador(String userId, List<ProximidadeService.AnomaliaProxima> anomalias) {
        messagingTemplate.convertAndSendToUser(
                userId,
                "/queue/alertas",
                new AlertaResponse("Obstáculos próximos detectados!", anomalias.size(), anomalias)
        );
    }

    public static class LocalizacaoRequest {
        private double latitude;
        private double longitude;
        private String userId;

        public double getLatitude() { return latitude; }
        public void setLatitude(double latitude) { this.latitude = latitude; }
        public double getLongitude() { return longitude; }
        public void setLongitude(double longitude) { this.longitude = longitude; }
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }
    }

    public static class AlertaResponse {
        private String mensagem;
        private int quantidadeAnomalias;
        private List<ProximidadeService.AnomaliaProxima> anomalias;

        public AlertaResponse(String mensagem, int quantidade, List<ProximidadeService.AnomaliaProxima> anomalias) {
            this.mensagem = mensagem;
            this.quantidadeAnomalias = quantidade;
            this.anomalias = anomalias;
        }

        public String getMensagem() { return mensagem; }
        public int getQuantidadeAnomalias() { return quantidadeAnomalias; }
        public List<ProximidadeService.AnomaliaProxima> getAnomalias() { return anomalias; }
    }
}