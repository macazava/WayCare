package com.example.waycare.Service;

import com.example.waycare.models.Utilizador;
import com.example.waycare.Repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> procurarPorId(Long id) {
        return utilizadorRepository.findById(id);
    }

    public Utilizador criar(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    public Utilizador atualizar(Long id, Utilizador novoUtilizador) {
        return utilizadorRepository.findById(id)
                .map(u -> {
                    u.setNome(novoUtilizador.getNome());
                    u.setEmail(novoUtilizador.getEmail());
                    u.setPassword(novoUtilizador.getPassword());
                    return utilizadorRepository.save(u);
                })
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
    }

    public void eliminar(Long id) {
        if (!utilizadorRepository.existsById(id)) {
            throw new RuntimeException("Utilizador não encontrado");
        }
        utilizadorRepository.deleteById(id);
    }
}
