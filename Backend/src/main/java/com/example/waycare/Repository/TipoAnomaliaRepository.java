package com.example.waycare.Repository;

import com.example.waycare.models.TipoAnomalia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TipoAnomaliaRepository extends JpaRepository<TipoAnomalia, Long> {
    Optional<TipoAnomalia> findByNome(String nome);

    List<TipoAnomalia> findByNomeContainingIgnoreCase(String nome);
}

