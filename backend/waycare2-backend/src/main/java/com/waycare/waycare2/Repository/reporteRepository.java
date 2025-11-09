package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reporteRepository {
    public interface ReporteRepository extends JpaRepository<reporte, Long> {

        List<reporte> findByEstado(String estado);

        List<reporte> findByUtilizador_Id(Long utiId);
    }
}
