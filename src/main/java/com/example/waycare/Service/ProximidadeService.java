package com.example.waycare.Service;

import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Anomalia;
import com.example.waycare.models.Localizacao;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProximidadeService {

  private static final double RAIO_TERRA_KM = 6371.0;
  private static final double DISTANCIA_ALERTA_METROS = 50.0;
  @Autowired private AnomaliaRepository anomaliaRepository;

  public double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);

    double a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2)
            + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distanciaKm = RAIO_TERRA_KM * c;

    return distanciaKm * 1000;
  }

  public List<AnomaliaProxima> encontrarAnomaliasPorPerto(double latitude, double longitude) {
    List<Anomalia> todasAnomalias = anomaliaRepository.findAll();
    List<AnomaliaProxima> anomaliasProximas = new ArrayList<>();

    for (Anomalia anomalia : todasAnomalias) {
      if (anomalia.getLocalizacao() != null) {
        Localizacao loc = anomalia.getLocalizacao();
        double distancia =
            calcularDistancia(latitude, longitude, loc.getLatitude(), loc.getLongitude());

        if (distancia <= DISTANCIA_ALERTA_METROS) {
          anomaliasProximas.add(
              new AnomaliaProxima(
                  anomalia.getId(),
                  anomalia.getDescricao(),
                  anomalia.getTipo().getNome(),
                  anomalia.getGrauPerigo(),
                  distancia,
                  loc.getLatitude(),
                  loc.getLongitude()));
        }
      }
    }

    return anomaliasProximas;
  }

  @Getter
  public static class AnomaliaProxima {

    private Long id;
    private String descricao;
    private String tipo;
    private String grauPerigo;
    private double distanciaMetros;
    private double latitude;
    private double longitude;

    public AnomaliaProxima(
        Long id,
        String descricao,
        String tipo,
        Object grauPerigo,
        double distanciaMetros,
        double latitude,
        double longitude) {
      this.id = id;
      this.descricao = descricao;
      this.tipo = tipo;
      this.grauPerigo = grauPerigo != null ? grauPerigo.toString() : "DESCONHECIDO";
      this.distanciaMetros = distanciaMetros;
      this.latitude = latitude;
      this.longitude = longitude;
    }
  }
}
