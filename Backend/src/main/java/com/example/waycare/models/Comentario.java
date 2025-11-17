package com.example.waycare.models;

import com.example.waycare.Service.NotificacaoService;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.antlr.v4.runtime.misc.LogManager;

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

    @Column(name = "com_texto", nullable = false)
    private String texto;

    @Column(name = "com_data", nullable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "uti_id")
    @JsonBackReference("utilizador-comentarios")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_id")
    @JsonBackReference("reporte-comentarios")
    private Reporte reporte;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
     }