package com.example.waycare.Service;

import com.example.waycare.Repository.ObstaculoRepository;
import com.example.waycare.models.Obstaculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObstaculoService {

    @Autowired
    private ObstaculoRepository obstaculoRepository;

    public List<Obstaculo> listarTodos() {
        return obstaculoRepository.findAll();
    }

    public Optional<Obstaculo> procurarPorId(Long id) {
        return obstaculoRepository.findById(id);
    }

    public Obstaculo criar(Obstaculo obstaculo) {
        return obstaculoRepository.save(obstaculo);
    }

    public Obstaculo atualizar(Long id, Obstaculo obstaculo) {
        Obstaculo existente = obstaculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obstáculo não encontrado"));
        existente.setDescricao(obstaculo.getDescricao());
        existente.setGrauPerigo(obstaculo.getGrauPerigo());
        existente.setCategoria(obstaculo.getCategoria());
        return obstaculoRepository.save(existente);
    }

    public void eliminar(Long id) {
        obstaculoRepository.deleteById(id);
    }
}
