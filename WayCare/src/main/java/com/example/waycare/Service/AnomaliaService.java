package com.example.waycare.Service;

import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Anomalia;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnomaliaService {

    @Autowired
    private AnomaliaRepository anomaliaRepository;

    public List<Anomalia> listarTodos() {
        return anomaliaRepository.findAll();
    }

    public Optional<Anomalia> procurarPorId(Long id) {
        return anomaliaRepository.findById(id);
    }

    public Anomalia criar(Anomalia anomalia) {
        return anomaliaRepository.save(anomalia);
    }

    public Anomalia atualizar(Long id, Anomalia anomalia) {
        Anomalia existente = anomaliaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obstáculo não encontrado"));
        existente.setDescricao(anomalia.getDescricao());
        existente.setGrauPerigo(anomalia.getGrauPerigo());
        existente.setTipoAnomalia(anomalia.getTipoAnomalia());
        return anomaliaRepository.save(existente);
    }

    public void eliminar(Long id) {
        anomaliaRepository.deleteById(id);
    }
}






