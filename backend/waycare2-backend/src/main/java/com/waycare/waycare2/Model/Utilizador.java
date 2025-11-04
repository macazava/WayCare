package com.waycare.waycare2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Utilizador")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    private String foto;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    // Um utilizador pode ter vários reportes
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<reporte> reportes;

    // Um utilizador pode ter vários comentários
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<comentario> comentarios;

    public Utilizador(String nome, String email, String password) {
    }

    // Define as datas automaticamente antes de criar/atualizar
    @PrePersist
    protected void onCreate() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        atualizadoEm = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizador that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);

    }

    public Object getId() {
        return null;
    }



    public Object getNome() {
        return nome;
    }

    public void setNome(Object nome) {
        this.nome = nome.toString();
    }

    public Object getEmail() {
        return null;
    }
    public Object getPassword() {
        return password;

    }
   public void setId(Long id){
        this.id = id;
   }
   public void setNome( String nome){
        this.nome = nome;
   }
   public void selEmail(String email){
        this.email = email;
   }

    public void setPassword(String password) {
        this.password = password;
    }
}

