package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
@Entity
@Getter
@Setter
@Table(name = "obstaculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class obstaculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obs_cat_id")
    private categoria categoria;

    private String descricao;

    private GrauPerigo grauPerigo;

    private String fotografia;


        public enum GrauPerigo{
        BAIXO,
        MEDIO,
        ALTO
    }


    public enum CategoriaObstaculo{

    }


    public Long getId() {
        return id;
    }

    public categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public GrauPerigo getGrauPerigo() {
        return grauPerigo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoria(categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;

    }

    public void setGrauPerigo(GrauPerigo grauPerigo) {
        this.grauPerigo = grauPerigo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        obstaculo obstaculo = (obstaculo) o;
        return Objects.equals(id, obstaculo.id) && Objects.equals(categoria, obstaculo.categoria) && Objects.equals(descricao, obstaculo.descricao) && Objects.equals(grauPerigo, obstaculo.grauPerigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoria, descricao, grauPerigo);
    }
}

