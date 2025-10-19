package repository;
import models.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ObstaculoRepository extends JpaRepository<Obstaculo, Long> {
    List<Obstaculo> findByGrauPerigo(String grauPerigo);
    List<Obstaculo> findByCategoria_Nome(String nomeCategoria);
}
