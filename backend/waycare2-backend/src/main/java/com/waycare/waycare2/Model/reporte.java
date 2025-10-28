package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rep_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_obs_id")
    private obstaculo obstaculo;

    @ManyToOne
    @JoinColumn(name = "rep_loc_id")
    private localizacao localizacao;

    private LocalDateTime data;

    private String estado;

    private String titulo; // novo campo
    @Column(length = 2000)
    private String descricao; // novo campo

    private LocalDateTime criadoEm; // novo campo
    private LocalDateTime atualizadoEm; // novo campo

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<Comentario> comentarios; // nova lista

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reporte")
    private List<Notificacao> notificacoes; // nova lista

    public enum EstadoReporte {
        PENDENTE,
        EM_PROCESSO,
        CONCLUIDO,
        CANCELADO
    }

    private String comentario; // mantém campo antigo

    // Getters e setters existentes
    public Long getId() { return id; }
    public Utilizador getUtilizador() { return utilizador; }
    public obstaculo getObstaculo() { return obstaculo; }
    public localizacao getLocalizacao() { return localizacao; }
    public LocalDateTime getData() { return data; }
    public String getEstado() { return estado; }
    public String getComentario() { return comentario; }
    public void setId(Long id) { this.id = id; }
    public void setUtilizador(Utilizador utilizador) { this.utilizador = utilizador; }
    public void setObstaculo(obstaculo obstaculo) { this.obstaculo = obstaculo; }
    public void setLocalizacao(localizacao localizacao) { this.localizacao = localizacao; }
    public void setData(LocalDateTime data) { this.data = data; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    // Getters e setters dos novos campos
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
    public List<Comentario> getComentarios() { return comentarios; }
    public void setComentarios(List<Comentario> comentarios) { this.comentarios = comentarios; }
    public List<Notificacao> getNotificacoes() { return notificacoes; }
    public void setNotificacoes(List<Notificacao> notificacoes) { this.notificacoes = notificacoes; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        reporte reporte = (reporte) o;
        return Objects.equals(id, reporte.id) &&
                Objects.equals(utilizador, reporte.utilizador) &&
                Objects.equals(obstaculo, reporte.obstaculo) &&
                Objects.equals(localizacao, reporte.localizacao) &&
                Objects.equals(data, reporte.data) &&
                Objects.equals(estado, reporte.estado) &&
                Objects.equals(comentario, reporte.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, utilizador, obstaculo, localizacao, data, estado, comentario);
    }
}

