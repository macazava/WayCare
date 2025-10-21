package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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

    @Column(name = "uti_nome")
    private String nome;

    @Column(name = "uti_email", unique = true)
    private String email;

    @Column(name = "uti_password")
    private String password;

    @OneToMany(mappedBy = "utilizador", cascade = CascadeType.ALL)
    private List<Reporte> reportes;
}
