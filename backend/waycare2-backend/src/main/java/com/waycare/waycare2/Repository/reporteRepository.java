package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface reporteRepository {
    Commentator findById(Long reportId);

    public interface ReporteRepository extends JpaRepository<reporte, Long> {

        List<reporte> findByEstado(String estado);

        List<reporte> findByUtilizador_Id(Long utiId);
    }
}
