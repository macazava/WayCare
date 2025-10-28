package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class Comentario {


    @Entity
    @Table(name = "comentario")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class comentario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String texto;

        private LocalDateTime criadoEm; // data de criação

        private LocalDateTime atualizaEm; // data de atualização


        @ManyToOne
        @JoinColumn(name = "Utilizador_id")
        private Utilizador Utilizador; //id_Utilizador
    }
}
