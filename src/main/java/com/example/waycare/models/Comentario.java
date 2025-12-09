package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
    public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uti_id", nullable = false)
    @JsonIgnoreProperties({"comentarios","password","tokenRecuperacao"})
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_id")
    private Reporte reporte;

    @Column(name = "com_texto", nullable = false)
    @jakarta.validation.constraints.NotBlank(message = "O comentário não pode estar vazio")
    private String texto;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @PrePersist
    private void onCreate(){
        if(dataCriacao == null) dataCriacao = LocalDateTime.now();
    }
}
