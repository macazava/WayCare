package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comentario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao {
    
        @ManyToOne
        private Obstaculo obstaculo;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 2000)
        private String texto;

        private LocalDateTime dataCriacao;

        @ManyToOne
        @JoinColumn(name = "utilizador_id")
        private Utilizador utilizador;




        @ManyToOne
        @JoinColumn(name = "reporte_id")
        private Reporte reporte;


        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTexto() { return texto; }
        public void setTexto(String texto) { this.texto = texto; }

        public LocalDateTime getDataCriacao() { return dataCriacao; }
        public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

        public Utilizador getUtilizador() { return utilizador; }
        public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }

        public Reporte getReporte() { return reporte; }
        public void setReporte(Reporte reporte) { this.reporte = reporte; }

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

    public class Comentario {
        public Object id;
    }
}

