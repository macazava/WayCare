package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Commentator;
import com.waycare.waycare2.Model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReporteRepository {
    Commentator findById(Long reportId);

    public interface reporteRepository extends JpaRepository<Reporte, Long> {

        List<Reporte> findByEstado(String estado);

        List<Reporte> findByUtilizador_Id(Long utiId);
    }
}
