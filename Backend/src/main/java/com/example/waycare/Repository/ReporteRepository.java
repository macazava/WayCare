package com.example.waycare.Repository;

import com.example.waycare.models.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByEstado(String estado);
    List<Reporte> findByUtilizadorId(Long utiId);
    List<Reporte> findByAnomaliaId(Long anoId);
    List<Reporte> findByLocalizacaoId(Long locId);
}

