package com.example.waycare.Repository;
import com.example.waycare.models.Localizacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
    List<Localizacao> findByEnderecoContaining(String endereco);
}
