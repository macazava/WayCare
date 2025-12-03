package com.example.waycare.Service;


import com.example.waycare.Repository.AnomaliaRepository;
import com.example.waycare.models.Anomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnomaliaService {

    @Autowired
    private AnomaliaRepository anomaliaRepository;

    public List<Anomalia> listarTodas() {
        return anomaliaRepository.findAll();
    }

    public Optional<Anomalia> procurarPorId(Long id) {
        return anomaliaRepository.findById(id);
    }

    public Anomalia criar(Anomalia anomalia) {
        return anomaliaRepository.save(anomalia);
    }

    public Anomalia atualizar(Long id, Anomalia novosDados) {
        Anomalia a = anomaliaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anomalia não encontrada"));
        a.setDescricao(novosDados.getDescricao());
        a.setEstado(novosDados.getEstado());
        return anomaliaRepository.save(a);
    }

    public void eliminar(Long id) {
        anomaliaRepository.deleteById(id);
    }
}

