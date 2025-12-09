package com.example.waycare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "anomalia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anomalia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ano_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_id")
    @JsonIgnoreProperties({"anomalias", "hibernateLazyInitializer", "handler"})
    @NotNull(message = "Tipo de anomalia é obrigatório")
    private TipoAnomalia tipo;

    @NotBlank(message = "Descrição é obrigatória")
    @Size(max = 500, message = "Descrição não pode ter mais de 500 caracteres")
    @Column(name = "ano_descricao")
    private String descricao;

    @Column(name = "ano_data_detecao")
    private LocalDate dataDetecao;

    @NotBlank(message = "Estado é obrigatório")
    @Column(name = "ano_estado")
    private String estado;

    @Column(name = "ano_severidade")
    private String severidade;

    @Column(name = "ano_grau_perigo")
    @Enumerated(EnumType.STRING)
    private GrauPerigo grauPerigo;

    @ManyToOne
    @JoinColumn(name = "ano_uti_id")
    @JsonIgnoreProperties({"anomalias", "reportes", "comentarios", "password", "tokenRecuperacao", "hibernateLazyInitializer", "handler"})
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "ano_loc_id")
    @JsonIgnoreProperties({"anomalias", "reportes", "notificacoes", "hibernateLazyInitializer", "handler"})
    private Localizacao localizacao;

    @Column(name = "data_registo")
    private LocalDateTime dataRegisto;

    @PrePersist
    protected void onCreate() {
        if (dataRegisto == null) {
            dataRegisto = LocalDateTime.now();
        }
        if (dataDetecao == null) {
            dataDetecao = LocalDate.now();
        }
    }
}