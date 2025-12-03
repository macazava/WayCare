package com.example.waycare.Service;

import DTO.RegisterRequestDTO;
import com.example.waycare.models.Utilizador;
import com.example.waycare.Repository.UtilizadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> procurarPorId(Long id) {
        return utilizadorRepository.findById(id);
    }

    public Utilizador criar(Utilizador utilizador) {
        return utilizadorRepository.save(utilizador);
    }

    public Utilizador atualizar(Long id, Utilizador novosDados) {
        Utilizador u = utilizadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
        u.setNome(novosDados.getNome());
        u.setEmail(novosDados.getEmail());
        u.setPassword(novosDados.getPassword());
        return utilizadorRepository.save(u);
    }

    public void eliminar(Long id) {
        if (!utilizadorRepository.existsById(id)) {
            throw new RuntimeException("Utilizador não encontrado");
        }
        utilizadorRepository.deleteById(id);

    }

    public boolean autenticar(String email, String password) {
        return false;
    }
    public Utilizador registarNovoUtilizador(RegisterRequestDTO dto) {
        Utilizador utilizador = new Utilizador();
        utilizador.setNome(dto.getNome());
        utilizador.setEmail(dto.getEmail());
        utilizador.setPassword(dto.getPassword());


        return utilizadorRepository.save(utilizador);
    }
}






