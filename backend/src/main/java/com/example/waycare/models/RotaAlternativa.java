package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RotaAlternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double latitudeInicio;

    @Column(nullable = false)
    private Double longitudeInicio;

    @Column(nullable = false)
    private Double latitudeDestino;

    @Column(nullable = false)
    private Double longitudeDestino;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String pontosIntermediosJson;
}

