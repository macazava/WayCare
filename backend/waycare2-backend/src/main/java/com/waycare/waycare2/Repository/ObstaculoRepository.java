package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Obstaculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObstaculoRepository {
    List<Obstaculo> findAll();

    Obstaculo save(Obstaculo obstaculo);

    void deleteById(Long id);

    List<Obstaculo> findByCategoria_Id(Long categoriaId);

    public interface obstaculoRepository extends JpaRepository<Obstaculo, Long> {


        List<Obstaculo> findByGrauPerigo(String grauPerigo);

        List<Obstaculo> findByCategoria_Nome(String nomeCategoria);
    }
}
