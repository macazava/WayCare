package com.example.waycare.Repository;

import com.example.waycare.models.TipoAnomalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoAnomaliaRepository extends JpaRepository<TipoAnomalia, Long> {
    List<TipoAnomalia> findByNomeContainingIgnoreCase(String nome);
}

