package DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String nome;
    private String email;
    private String password;
}

