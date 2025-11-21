package com.example.waycare.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

    @Email
    @NotBlank
    @Column(name = "uti_email", unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "uti_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "loc_id", nullable = true)
    private Localizacao localizacao;


    @Column(name = "uti_nome", nullable = false)
    private String nome;

    @Column(name = "uti_data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "uti_genero")
    private String genero;

    @Column(name = "uti_estado", nullable = false)
    private Boolean ativo = true;

    @Column(name = "uti_verificado", nullable = false)
    private Boolean emailVerificado = false;

    @Column(name = "uti_token_recuperacao")
    private String tokenRecuperacao;

    @Column(name = "uti_ultimo_login")
    private LocalDateTime ultimoLogin;

    @Column(name = "uti_tentativas_login")
    private Integer tentativasLogin = 0;


    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("utilizador-reportes")
    private List<Reporte> reportes;

    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("utilizador-comentarios")
    private List<Comentario> comentarios;


    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Fotografia> fotografias;


    @PrePersist
    protected void onCreate() {
        if (ativo == null) ativo = true;
        if (emailVerificado == null) emailVerificado = false;
        if (tentativasLogin == null) tentativasLogin = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}