package DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LocalizacaoResponseDTO {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String descricao;
    private String endereco;
}
