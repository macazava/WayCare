package DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    @NotBlank(message = "O token é obrigatório.")
    private String token;

    @NotBlank(message = "A nova password é obrigatória.")
    @Size(min = 8, message = "A password deve ter pelo menos 8 caracteres.")
    private String novaPassword;

    @NotBlank(message = "A confirmação é obrigatória.")
    private String confirmarNovaPassword;
}
