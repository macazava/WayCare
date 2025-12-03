package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


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
    @JoinColumn(name = "uti_id")
    @JsonIgnoreProperties("comentarios")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "ano_id")
    @JsonIgnoreProperties("comentarios")
    private Anomalia anomalia;


    @Column(name = "com_texto", nullable = false)
    private String texto;

    @Column(name = "data_criacao", nullable = false,
            columnDefinition = "timestamp default current_timestamp")
    private LocalDateTime dataCriacao;


    @ManyToOne
    @JoinColumn(name = "rep_id")
    private Reporte reporte;
}