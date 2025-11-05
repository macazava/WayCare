package com.example.waycare.Repository;

import com.example.waycare.models.Reporte;
import com.example.waycare.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByUtilizador(Utilizador utilizador);
    List<Reporte> findByAnomaliaDescricaoContainingIgnoreCase(String descricao);

    @Query("SELECT r FROM Reporte r LEFT JOIN r.anomalia a " +
           "WHERE (a.descricao IS NOT NULL AND LOWER(a.descricao) LIKE LOWER(CONCAT('%', :texto, '%'))) " +
           "OR (r.tipoPersonalizado IS NOT NULL AND LOWER(r.tipoPersonalizado) LIKE LOWER(CONCAT('%', :texto, '%')))" )
    List<Reporte> searchByTipoOrCustom(@Param("texto") String texto);


}

