package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "utilizador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uti_id")
    private Long id;

    @Column(name = "uti_nome", nullable = false)
    private String nome;

    // Corrigido: email não pode ser nulo, senão o registo falha
    @Column(name = "uti_email", unique = true, nullable = false)
    private String email;

    @Column(name = "uti_password", nullable = false)
    private String password;

    // Relação com Reporte
    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("utilizador-reportes")
    private List<Reporte> reportes;
}


