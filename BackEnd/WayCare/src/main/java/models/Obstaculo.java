package models;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "obstaculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Obstaculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "obs_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "obs_cat_id")
    private Categoria categoria;

    @Column(name = "obs_descricao")
    private String descricao;

    @Column(name = "obs_grau_perigo")
    private String grauPerigo;
}
