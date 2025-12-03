package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zon_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ano_id")
    @JsonIgnoreProperties("zonas")
    private Anomalia anomalia;


    @Column(name = "localizacao", nullable = false)
    private String localizacao;

}
