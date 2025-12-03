package com.example.waycare.Repository;

import com.example.waycare.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByAnomaliaId(Long anoId);
    List<Comentario> findByUtilizadorId(Long utiId);
    List<Comentario> findByReporteId(Long repId);
}
