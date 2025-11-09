package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class Notificacao {
    @Entity
    @Table(name = "comentario")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public class Comentario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        // Texto do comentário
        @Column(length = 2000)
        private String texto;

        // Data de criação do comentário
        private LocalDateTime dataCriacao;

        // Autor do comentário (opcional: pode ser um utilizador)
        @ManyToOne
        @JoinColumn(name = "utilizador_id")
        private Utilizador utilizador;

        // Relacionamento ManyToOne para o reporte correspondente
        @ManyToOne
        @JoinColumn(name = "reporte_id")
        private reporte reporte;

        // Getters e setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTexto() { return texto; }
        public void setTexto(String texto) { this.texto = texto; }

        public LocalDateTime getDataCriacao() { return dataCriacao; }
        public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

        public Utilizador getUtilizador() { return utilizador; }
        public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }

        public reporte getReporte() { return reporte; }
        public void setReporte(reporte reporte) { this.reporte = reporte; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Comentario that = (Comentario) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
