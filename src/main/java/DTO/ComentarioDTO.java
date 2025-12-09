package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ComentarioDTO {

    private Long id;

    @NotBlank (message = "O comentário não pode estar vazio.")
    @jakarta.validation.constraints.Size(min = 6, max = 100, message = "O comentário deve ter pelo menos 6 caracteres")
    private String texto;

    @NotNull(message = "É obrigatório indicar o utilizador.")
    private Long utilizadorId;

    @NotNull(message = "É obrigatório indicar o reporte.")
    private Long reporteId;

    private LocalDateTime dataCriacao;
}
