package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface localizacaoRepository {
    public interface LocalizacaoRepository extends JpaRepository<localizacao, Long> {

    }
}