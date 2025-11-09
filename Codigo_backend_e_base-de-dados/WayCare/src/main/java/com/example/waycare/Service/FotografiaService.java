package com.example.waycare.Service;

import com.example.waycare.Repository.FotografiaRepository;
import com.example.waycare.models.Fotografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotografiaService {

    @Autowired
    private FotografiaRepository fotografiaRepository;

    public List<Fotografia> listarTodos() {
        return fotografiaRepository.findAll();
    }

    public Optional<Fotografia> procurarPorId(Long id) {
        return fotografiaRepository.findById(id);
    }

    public Fotografia criar(Fotografia fotografia) {
        return fotografiaRepository.save(fotografia);
    }

    public Fotografia atualizar(Long id, Fotografia fotografia) {
        Fotografia existente = fotografiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fotografia não encontrada"));
        existente.setUrl(fotografia.getUrl());
        existente.setReporte(fotografia.getReporte());
        return fotografiaRepository.save(existente);
    }

    public void eliminar(Long id) {
        fotografiaRepository.deleteById(id);
    }
}
