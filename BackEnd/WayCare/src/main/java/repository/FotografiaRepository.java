package repository;
import models.Fotografia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FotografiaRepository extends JpaRepository<Fotografia, Long> {
    List<Fotografia> findByReporte_Id(Long repId);
}
