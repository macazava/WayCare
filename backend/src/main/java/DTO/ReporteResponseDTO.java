package DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReporteResponseDTO {

    private Long id;

    private Long utilizadorId;
    private String nomeUtilizador;

    private Long anomaliaId;
    private String nomeAnomalia;

    private Long localizacaoId;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private String descricaoLocalizacao;

    private String fotoUrl;

    private String estadoReporte;

    private String descricao;

    private String tipoPersonalizado;

    private LocalDateTime dataRegisto;

    private String zona;

    private String grauPerigo;

    private Long rotaAlternativaId;
}
