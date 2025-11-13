package com.example.waycare.Service;

import com.example.waycare.Repository.TipoAnomaliaRepository;
import com.example.waycare.models.TipoAnomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoAnomaliaService {

    @Autowired
    private TipoAnomaliaRepository tipoAnomaliaRepository;

    public List<TipoAnomalia> listarTodos() {
        return tipoAnomaliaRepository.findAll();
    }

    public Optional<TipoAnomalia> procurarPorId(Long id) {
        return tipoAnomaliaRepository.findById(id);
    }

    public TipoAnomalia criar(TipoAnomalia tipoAnomalia) {
        return tipoAnomaliaRepository.save(tipoAnomalia);
    }

    public TipoAnomalia atualizar(Long id, TipoAnomalia tipoAnomalia) {
        TipoAnomalia existente = tipoAnomaliaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de anomalia não encontrado"));

        existente.setNome(tipoAnomalia.getNome());
        return tipoAnomaliaRepository.save(existente);
    }

    public void eliminar(Long id) {
        if (!tipoAnomaliaRepository.existsById(id)) {
            throw new RuntimeException("Tipo de anomalia não encontrado");
        }
        tipoAnomaliaRepository.deleteById(id);
    }

    public List<TipoAnomalia> procurarPorNome(String nome) {
        return tipoAnomaliaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
