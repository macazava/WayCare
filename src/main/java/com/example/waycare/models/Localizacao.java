package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "localizacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private Long id;

    @Column(name = "loc_latitude", nullable = false)
    private Double latitude;

    @Column(name = "loc_longitude", nullable = false)
    private Double longitude;

    @Column(name = "loc_endereco", nullable = false)
    private String endereco;

    @Column(name = "loc_descricao")
    private String descricao;
}
