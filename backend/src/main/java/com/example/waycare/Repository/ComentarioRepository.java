package com.example.waycare.Repository;

    import com.example.waycare.models.Comentario;
    import java.util.List;
    import org.springframework.data.jpa.repository.JpaRepository;

    public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
        List<Comentario> findByUtilizadorId(Long utiId);
        List<Comentario> findByReporteId(Long repId);
    }
