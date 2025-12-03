package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JoinColumn(name = "tipo_id")
    private TipoAnomalia tipo;

    @Column(name = "ano_descricao")
    private String descricao;

    @Column(name = "ano_data_detecao")
    private LocalDate dataDetecao;

    @Column(name = "ano_estado")
    private String estado;

    @Column(name = "ano_severidade")
    private String severidade;

    @Column(name = "ano_grau_perigo")
    @Enumerated(EnumType.STRING)
    private GrauPerigo grauPerigo;

    @ManyToOne
    @JoinColumn(name = "ano_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "ano_loc_id")
    private Localizacao localizacao;

    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;
}
