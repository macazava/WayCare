package com.example.waycare.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "loc_latitude", nullable = false)
    private Double latitude;

    @Column(name = "loc_longitude", nullable = false)
    private Double longitude;

    @Column(name="loc_endereco")
    private String endereco;

    @OneToMany(mappedBy = "localizacao", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"localizacao", "anomalia", "utilizador", "fotografias"})
    private List<Reporte> reportes = new ArrayList<>();
}

