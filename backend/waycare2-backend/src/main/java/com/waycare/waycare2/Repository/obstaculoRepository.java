package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface obstaculoRepository {
    public interface ObstaculoRepository extends JpaRepository<obstaculo, Long> {

        // Podes criar métodos automáticos baseados em nomes
        List<obstaculo> findByGrauPerigo(String grauPerigo);

        List<obstaculo> findByCategoria_Nome(String nomeCategoria);
    }
}
