package com.example.waycare.Repository;

import com.example.waycare.models.TipoAnomalia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TipoAnomaliaRepository extends JpaRepository<TipoAnomalia, Long> {

    List<TipoAnomalia> findByNomeContainingIgnoreCase(String nome);

    Optional<TipoAnomalia> findByNome(String nome);
}

