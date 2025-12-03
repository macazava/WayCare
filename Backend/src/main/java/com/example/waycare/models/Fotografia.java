package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(name = "foto_nome")
    private String nome;

    @Column(name = "foto_caminho")
    private String caminho;

    @Column(name = "foto_mime")
    private String mime;

    @Column(name = "foto_tamanho")
    private Long tamanho;

    @Column(name = "foto_descricao")
    private String descricao;

    @Column(name = "fot_data_upload")
    private LocalDateTime dataUpload;

    @Column(name = "foto_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "fot_rep_id")
    private Reporte reporte;

    @ManyToOne
    @JoinColumn(name = "fot_ano_id")
    private Anomalia anomalia;

    @ManyToOne
    @JoinColumn(name = "fot_uti_id")
    private Utilizador utilizador;
}


