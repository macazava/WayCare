package DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterDTO {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 60, message = "O nome deve ter entre 3 e 60 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    private String email;

    @NotBlank(message = "A password é obrigatória.")
    @Size(min = 8, message = "A password deve ter pelo menos 8 caracteres.")
    private String password;

    @NotBlank(message = "Confirmação da password é obrigatória.")
    private String confirmarPassword;

    @Past(message = "A data de nascimento deve ser anterior à data atual.")
    private LocalDate dataNascimento;

    @Pattern(regexp = "MASCULINO|FEMININO|OUTRO", message = "Género inválido (MASCULINO, FEMININO ou OUTRO)")
    private String genero;

    @Pattern(regexp = "\\d{9}", message = "O número de telemóvel deve ter exatamente 9 dígitos.")
    private String telemovel;
}
