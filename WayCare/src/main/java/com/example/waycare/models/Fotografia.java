package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "fotografia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fotografia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "foto_id")
    private Long id;

    @Column(name = "foto_nome", nullable = false)
    private String nome;

    @Column(name = "foto_url")
    private String url;

    @Column(name = "foto_caminho", nullable = false)
    private String caminho;

    // Tipo MIME (ex: image/jpeg)
    @Column(name = "foto_mime")
    private String mime;

    @Column(name = "foto_tamanho")
    private Long tamanho;

    @ManyToOne
    @JoinColumn(name = "foto_rep_id", nullable = false)
    @JsonBackReference
    private Reporte reporte;
}


