package com.example.waycare.models;


import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "rep_estado")
    private String estado;

    @Column(name = "rep_descricao")
    private String descricao;

    @Column(name = "rep_tipo_personalizado")
    private String tipoPersonalizado;

    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;
}



