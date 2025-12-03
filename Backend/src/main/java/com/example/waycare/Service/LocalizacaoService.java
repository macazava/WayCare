package com.example.waycare.Service;

import com.example.waycare.Repository.LocalizacaoRepository;
import com.example.waycare.models.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    public List<Localizacao> listarTodas() {
        return localizacaoRepository.findAll();
    }

    public Localizacao criar(Localizacao loc) {
        return localizacaoRepository.save(loc);
    }
}
