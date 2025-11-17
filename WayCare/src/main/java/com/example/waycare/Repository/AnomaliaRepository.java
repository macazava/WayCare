package com.example.waycare.Repository;
import com.example.waycare.models.Anomalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnomaliaRepository extends JpaRepository<Anomalia, Long> {
    @Query("SELECT a FROM Anomalia a JOIN a.reportes r JOIN r.localizacao l WHERE l.latitude IS NOT NULL AND l.longitude IS NOT NULL")
    List<Anomalia> findAllByLocalizacao();

}

