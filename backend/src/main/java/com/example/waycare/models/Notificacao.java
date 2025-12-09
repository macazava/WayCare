package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "not_id")
    private Long id;

    @Column(name = "not_mensagem", nullable = false)
    private String mensagem;

    @Column(name = "not_tipo")
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "ano_id")
    private Anomalia anomalia;

    @ManyToOne
    @JoinColumn(name = "not_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "loc_id")
    private Localizacao localizacao;

    @Column(name = "not_lida")
    private Boolean lida = false;

    @Column(name = "not_data_envio")
    private LocalDateTime dataEnvio;
}
