package com.example.waycare.models;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "tipo_anomalia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAnomalia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_id")
    private Long id;

    @Column(name = "tipo_nome", nullable = false, unique = true)
    private String nome;
}


