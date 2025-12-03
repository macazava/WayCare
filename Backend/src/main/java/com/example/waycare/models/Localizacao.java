package com.example.waycare.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "loc_latitude")
    private Double latitude;

    @Column(name = "loc_longitude")
    private Double longitude;

    @Column(name = "loc_descricao")
    private String descricao;

    @Column(name = "loc_endereco")
    private String endereco;
     }
