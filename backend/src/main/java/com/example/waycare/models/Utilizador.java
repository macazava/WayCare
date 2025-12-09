package com.example.waycare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "utilizador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uti_id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, max = 60, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @NotBlank(message = "O email é obrigatório.")
    @Email(message = "Formato de email inválido.")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A password é obrigatória.")
    @Size(min = 8, message = "A password deve ter pelo menos 8 caracteres.")
    private String password;

    @Past(message = "A data tem de ser anterior à data atual.")
    private LocalDate dataNascimento;

    @Pattern(regexp = "MASCULINO|FEMININO|OUTRO", message = "Género inválido")
    private String genero;

    private Boolean estado = true;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private Boolean verificado = false;

    @Pattern(regexp = "\\d{9}", message = "O número deve ter 9 dígitos.")
    private String telemovel;

    private String tokenRecuperacao;

    private LocalDateTime ultimoLogin;

    @Min(0)
    @Max(value = 5, message = "Máximo de 5 tentativas")
    private Integer tentativasLogin = 0;
}


