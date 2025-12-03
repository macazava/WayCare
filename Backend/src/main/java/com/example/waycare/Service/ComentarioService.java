package com.example.waycare.Service;


import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.Repository.ComentarioRepository;

import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.models.Anomalia;
import com.example.waycare.models.Comentario;

import com.example.waycare.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private AnomaliaRepository anomaliaRepository;

    public Comentario criar(Comentario comentario) {
        Utilizador u = utilizadorRepository.findById(comentario.getUtilizador().getId())
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        Anomalia a = anomaliaRepository.findById(comentario.getAnomalia().getId())
                .orElseThrow(() -> new RuntimeException("Anomalia não encontrada"));

        comentario.setUtilizador(u);
        comentario.setAnomalia(a);

        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public List<Comentario> listarPorAnomalia(Long anoId) {
        return comentarioRepository.findByAnomaliaId(anoId);
    }

    public List<Comentario> listarPorUtilizador(Long utiId) {
        return comentarioRepository.findByUtilizadorId(utiId);
    }
}
