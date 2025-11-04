package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Obstaculo;
import com.waycare.waycare2.Model.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface obstaculoRepository {
    List<Obstaculo> findAll();

    Obstaculo save(Obstaculo obstaculo);

    void deleteById(Long id);

    public interface ObstaculoRepository extends JpaRepository<Obstaculo, Long> {

        // Podes criar métodos automáticos baseados em nomes
        List<Obstaculo> findByGrauPerigo(String grauPerigo);

        List<Obstaculo> findByCategoria_Nome(String nomeCategoria);
    }
}
