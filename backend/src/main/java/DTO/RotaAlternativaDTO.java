package DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class RotaAlternativaDTO {

    private Long id;

    @NotNull    (message = "O nome da rota é obrigatório.")
    private String nome;

    @NotNull(message = "A latitude de início é obrigatória.")
    private Double latitudeInicio;

    @NotNull(message = "A longitude de início é obrigatória.")
    private Double longitudeInicio;

    @NotNull(message = "A latitude de destino é obrigatória.")
    private Double latitudeDestino;

    @NotNull(message = "A longitude de destino é obrigatória.")
    private Double longitudeDestino;

    @NotEmpty(message = "A rota deve ter pelo menos um ponto intermédio.")
    private List<PontoIntermedioDTO> pontosIntermedios;
}
