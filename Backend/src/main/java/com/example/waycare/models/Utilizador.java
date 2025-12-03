package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "uti_nome", nullable = false)
    private String nome;

    @Column(name = "uti_email", nullable = false, unique = true)
    private String email;

    @Column(name = "uti_password", nullable = false)
    private String password;

    @Column(name = "uti_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "uti_genero")
    private String genero;

    @Column(name = "uti_estado")
    private Boolean estado = true;

    @Column(name = "uti_verificado")
    private Boolean verificado = false;

    @Column(name = "uti_token_recuperacao")
    private String tokenRecuperacao;

    @Column(name = "uti_ultimo_login")
    private LocalDateTime ultimoLogin;

    @Column(name = "uti_tentativas_login")
    private Integer tentativasLogin = 0;
     }