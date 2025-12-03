package com.example.waycare.Repository;
import com.example.waycare.models.Anomalia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnomaliaRepository extends JpaRepository<Anomalia, Long> {
    List<Anomalia> findByEstado(String estado);
    List<Anomalia> findByUtilizadorId(Long utiId);
    List<Anomalia> findByLocalizacaoId(Long locId);
}

