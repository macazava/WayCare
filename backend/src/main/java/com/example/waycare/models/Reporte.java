package com.example.waycare.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


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
    @JoinColumn(name = "rep_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_ano_id")
    private Anomalia anomalia;

    @ManyToOne
    @JoinColumn(name = "rep_loc_id")
    private Localizacao localizacao;

    @Column(name = "rep_foto_url")
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "rep_estado", nullable = false)
    private EstadoReporte estadoReporte;

    @Column(name = "rep_descricao")
    private String descricao;

    @Column(name = "rep_tipo_personalizado")
    private String tipoPersonalizado;

    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;

    @Enumerated(EnumType.STRING)
    @Column(name = "rep_zona", nullable = false)
    private Zona zona;

    @Enumerated(EnumType.STRING)
    @Column(name = "rep_grau_perigo", nullable = false)
    private GrauPerigo grauPerigo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rota_id")
    private RotaAlternativa rotaAlternativa;



}



