package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "anomalia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anomalia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ano_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tip_id", nullable = false)
    @JsonBackReference("tipoAnomalia-anomalias")
    private TipoAnomalia tipoAnomalia;

    @OneToMany(mappedBy = "anomalia", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("anomalia")
    private List<Reporte> reportes;

    @Column(name = "ano_descricao")
    private String descricao; // Local onde escrevemos a descrição do problema

    @Column(name = "ano_grau_perigo")
    private String grauPerigo;
}

