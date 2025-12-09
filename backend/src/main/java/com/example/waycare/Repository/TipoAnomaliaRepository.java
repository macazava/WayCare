package com.example.waycare.Repository;

import com.example.waycare.models.TipoAnomalia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoAnomaliaRepository extends JpaRepository<TipoAnomalia, Long> {

    List<TipoAnomalia> findByNomeContainingIgnoreCase(String nome);
}

