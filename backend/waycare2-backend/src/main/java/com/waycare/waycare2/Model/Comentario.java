package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


    @Table(name = "comentario")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Comentario {

        @ManyToOne
        @JoinColumn(name = "utilizador_id")
        private Utilizador utilizador;

        @ManyToOne
        @JoinColumn
        private Obstaculo obstaculo;

        @ManyToOne
        @JoinColumn(name = "reporte_id")
        private Reporte reporte;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        private String texto;

        private LocalDateTime criadoEm; // data de criação

        private LocalDateTime atualizaEm; // data de atualização

    }


