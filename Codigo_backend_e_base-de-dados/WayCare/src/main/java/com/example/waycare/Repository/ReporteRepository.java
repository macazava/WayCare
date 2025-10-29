package com.example.waycare.Repository;

import com.example.waycare.models.Reporte;
import com.example.waycare.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {
    List<Reporte> findByUtilizador(Utilizador utilizador);
    List<Reporte> findByObstaculoDescricaoContainingIgnoreCase(String descricao);

}

