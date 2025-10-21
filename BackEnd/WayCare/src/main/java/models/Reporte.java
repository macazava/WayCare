package models;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "reporte")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "rep_uti_id")
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_obs_id")
    private Obstaculo obstaculo;

    @ManyToOne
    @JoinColumn(name = "rep_loc_id")
    private Localizacao localizacao;

    @Column(name = "rep_data")
    private LocalDateTime data;

    @Column(name = "rep_estado")
    private String estado;

    @Column(name = "rep_comentario")
    private String comentario;
}
