package DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PontoIntermedioDTO {

    @NotNull(message = "A latitude do ponto intermédio é obrigatória.")
    private Double lat;

    @NotNull(message = "A longitude do ponto intermédio é obrigatória.")
    private Double lng;
}
