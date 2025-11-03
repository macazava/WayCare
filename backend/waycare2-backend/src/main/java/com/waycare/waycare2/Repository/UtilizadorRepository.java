package com.waycare.waycare2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.waycare.waycare2.Model.Utilizador;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    Optional<Utilizador> findByEmail(String email);
}

