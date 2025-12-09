package com.example.waycare.Repository;

import com.example.waycare.models.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    Optional<Utilizador> findByEmail(String email);

    Optional<Utilizador> findByTokenRecuperacao(String token);
}

