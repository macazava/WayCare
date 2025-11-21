package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "ano_descricao", nullable = false)
    private String descricao;

    @Column(name = "ano_data_detecao")
    private LocalDate dataDetecao = LocalDate.now();

    @Column(name = "ano_estado")
    private String estado = "Pendente"; // valores: Pendente, Em análise, Resolvida

    @Column(name = "ano_severidade")
    private String severidade; // opcional: Leve, Moderado, Grave

    // Relações
    @ManyToOne
    @JoinColumn(name = "ano_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JsonBackReference
    private TipoAnomalia tipoAnomalia;

    @ManyToOne
    @JoinColumn(name = "ano_loc_id")
    private Localizacao localizacao;

    @OneToMany(mappedBy = "anomalia", cascade = CascadeType.ALL)
    private java.util.List<Fotografia> fotografias;

     }
