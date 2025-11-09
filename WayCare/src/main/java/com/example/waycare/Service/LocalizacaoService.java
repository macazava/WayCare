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

    public List<Localizacao> listarTodos() {
        return localizacaoRepository.findAll();
    }

    public Optional<Localizacao> procurarPorId(Long id) {
        return localizacaoRepository.findById(id);
    }

    public Localizacao criar(Localizacao localizacao) {
        return localizacaoRepository.save(localizacao);
    }

    public Localizacao atualizar(Long id, Localizacao localizacao) {
        Localizacao existente = localizacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Localização não encontrada"));
        existente.setLatitude(localizacao.getLatitude());
        existente.setLongitude(localizacao.getLongitude());
        return localizacaoRepository.save(existente);
    }

    public void eliminar(Long id) {
        localizacaoRepository.deleteById(id);
    }
}
