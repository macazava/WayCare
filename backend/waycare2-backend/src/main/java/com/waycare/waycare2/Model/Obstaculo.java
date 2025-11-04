package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "obstaculo")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Obstaculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obs_cat_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "utilizador_id")
    private Utilizador utilizador;

    @OneToMany(mappedBy = "obstaculo")
    private List<Notificacao.Comentario> comentarios;

    @NotBlank
    private String descricao;

    @Enumerated(EnumType.STRING)
    private GrauPerigo grauPerigo;

    private LocalDateTime criadoEm;

    @OneToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String fotografia;

    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
    }

    public enum GrauPerigo {
        BAIXO,
        MEDIO,
        ALTO
    }

    public enum Tipo {
        RampasInexistentes,
        PasseiosDanificados,
        PassadeirasMalSituadas,
        ZonasPerigosas,
        Outros
    }
}

