package com.waycare.waycare2.Model;


import jakarta.persistence.*;

@Entity
    public class Commentator {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        @ManyToOne
        @JoinColumn(name = "report_id")
        private reporte reporte;

        @ManyToOne
        @JoinColumn(name = "affiliation_id")
        private Affiliation affiliation;

        // Construtores
        public Commentator() {

        }

        public Commentator(String nome, reporte reporte, Affiliation affiliation) {
            this.nome = nome;
            this.reporte = reporte;
            this.affiliation = affiliation;
        }

        // Getters e Setters
        public Long getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public reporte getReporte() {
            return reporte;
        }

        public void setReporte(reporte reporte) {
            this.reporte = reporte;
        }

        public Affiliation getAffiliation() {
            return affiliation;
        }

        public void setAffiliation(Affiliation affiliation) {
            this.affiliation = affiliation;
        }

    public Commentator orElse(Object o) {
        return null;
    }

    public void setReport(reporte report) {
    }
}

