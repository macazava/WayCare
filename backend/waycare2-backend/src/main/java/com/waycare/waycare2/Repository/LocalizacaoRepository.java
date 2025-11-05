package com.waycare.waycare2.Repository;

import com.waycare.waycare2.Model.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalizacaoRepository {
    public interface localizacaoRepository extends JpaRepository<Localizacao, Long> {

    }
}