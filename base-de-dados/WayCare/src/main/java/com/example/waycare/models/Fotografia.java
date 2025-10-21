package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "foto_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "foto_rep_id")
    private Reporte reporte;
}

