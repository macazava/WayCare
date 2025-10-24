package com.example.waycare.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonBackReference
    private Utilizador utilizador;

    @ManyToOne
    @JoinColumn(name = "rep_obs_id")
    private Obstaculo obstaculo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rep_loc_id")
    @JsonManagedReference
    private Localizacao localizacao;

    @OneToMany(mappedBy = "reporte", cascade = CascadeType.ALL)
    private List<Fotografia> fotografias;

    @Column(name = "rep_estado")
    private String estado;

    @Column(name = "rep_data")
    private LocalDate data = LocalDate.now();


}

