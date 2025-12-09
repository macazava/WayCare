package DTO;

import com.example.waycare.models.GrauPerigo;
import com.example.waycare.models.Zona;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReporteCreateDTO {

    @NotNull(message = "O ID do utilizador é obrigatório")
    private Long utilizadorId;

    @NotNull(message = "O ID da anomalia é obrigatório")
    private Long anomaliaId;

    @Size(min = 5, message = "Descrição deve ter no mínimo 5 caracteres")
    private String descricao;

    private String fotoUrl;

    private String tipoPersonalizado;

    @NotNull(message = "A zona é obrigatória")
    private Zona zona;

    @NotNull(message = "O Grau de Perigo é obrigatório")
    private GrauPerigo grauPerigo;

    @NotNull(message = "Latitude é obrigatória")
    private Double latitude;

    @NotNull(message = "Longitude é obrigatória")
    private Double longitude;

    private String endereco; // Opcional
}

