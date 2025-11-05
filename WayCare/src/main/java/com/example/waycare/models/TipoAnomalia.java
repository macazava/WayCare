package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "TipoAnomalia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoAnomalia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tip_id")
    private Long id;

    @Column(name = "tip_nome", nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "tipoAnomalia", cascade = CascadeType.ALL)
    @JsonManagedReference("tipoAnomalia-anomalias")
    private List<Anomalia> anomalias;

    //Tipo de anomalia: Aparece no dropdown do report.
}


