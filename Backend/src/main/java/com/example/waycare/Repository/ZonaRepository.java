package com.example.waycare.Repository;

import com.example.waycare.models.Zona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    List<Zona> findByLocalizacao(String localizacao);
    List<Zona> findByAnomaliaId(Long anoId);
}
