package com.example.waycare.Service;

import com.example.waycare.models.Utilizador;
import com.example.waycare.Repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilizadorService(UtilizadorRepository utilizadorRepository, PasswordEncoder passwordEncoder) {
        this.utilizadorRepository = utilizadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Listar todos
    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    // Procurar por ID
    public Optional<Utilizador> procurarPorId(Long id) {
        return utilizadorRepository.findById(id);
    }

    // Procurar por Email
    public Optional<Utilizador> procurarPorEmail(String email) {
        return Optional.ofNullable(utilizadorRepository.findByEmail(email));
    }

    // Atualizar utilizador
    public Utilizador atualizar(Long id, Utilizador novoUtilizador) {
        return utilizadorRepository.findById(id)
                .map(u -> {
                    u.setNome(novoUtilizador.getNome());
                    u.setEmail(novoUtilizador.getEmail());

                    // só re-encripta se o utilizador mudou a password
                    if (!novoUtilizador.getPassword().equals(u.getPassword())) {
                        u.setPassword(passwordEncoder.encode(novoUtilizador.getPassword().toString()));
                    }

                    return utilizadorRepository.save(u);
                })
                .orElseThrow(() -> new RuntimeException("Utilizador não encontrado"));
    }

    // Eliminar utilizador
    public void eliminar(Long id) {
        if (!utilizadorRepository.existsById(id)) {
            throw new RuntimeException("Utilizador não encontrado");
        }
        utilizadorRepository.deleteById(id);
    }

    // Registar
    public Utilizador registar(Utilizador utilizador) {
        utilizador.setPassword(passwordEncoder.encode((CharSequence) utilizador.getPassword()));
        return utilizadorRepository.save(utilizador);
    }

    // Autenticar (login)
    public boolean autenticar(String email, String password) {
        Optional<Utilizador> opt = Optional.ofNullable(utilizadorRepository.findByEmail(email));
        if (opt.isEmpty()) return false;

        Utilizador u = opt.get();
        return passwordEncoder.matches(password, (String) u.getPassword());
    }
}
