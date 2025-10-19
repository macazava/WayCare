package models;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "uti_email")
    private String email;

    @Column(name = "uti_password")
    private String password;

    @Column(name = "uti_telefone")
    private String telefone;
}
