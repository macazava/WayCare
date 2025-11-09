package models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "localizacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private Long id;

    @Column(name = "loc_latitude")
    private Double latitude;

    @Column(name = "loc_longitude")
    private Double longitude;

    @Column(name = "loc_endereco")
    private String endereco;
}
