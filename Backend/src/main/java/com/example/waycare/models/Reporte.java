package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rep_uti_id", nullable = false)
    @JsonBackReference("utilizador-reportes")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_ano_id", nullable = true)
    @JsonIgnoreProperties("reportes")
    private Anomalia anomalia;

    @Column(name = "rep_tipo_personalizado")
    private String tipoPersonalizado; //apenas para anomalias personalizadas

    @Column(name = "rep_foto_url")
    private String fotoUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rep_loc_id", nullable = true)
    @JsonIgnoreProperties({"reportes"})
    private Localizacao localizacao;

    @OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("reporte")
    private List<Fotografia> fotografias;

    @Column(name = "rep_estado")
    private String estado = "Pendente";

    @Column(name = "rep_data")
    private LocalDate data = LocalDate.now();

    @Column(name = "rep_descricao")
    private String descricao;

    }


