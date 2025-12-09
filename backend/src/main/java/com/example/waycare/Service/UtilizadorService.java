package com.example.waycare.Service;

import DTO.LoginDTO;
import DTO.RegisterDTO;
import DTO.ResetPasswordDTO;
import com.example.waycare.Repository.UtilizadorRepository;
import com.example.waycare.models.Role;
import com.example.waycare.models.Utilizador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UtilizadorService {

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    public Optional<Utilizador> procurarPorId(Long id) {
        return utilizadorRepository.findById(id);
    }


    public Utilizador registarNovoUtilizador(@Valid RegisterDTO dto) {

        if (utilizadorRepository.findByEmail(dto.getEmail()).isPresent())
            throw new RuntimeException("Este email já está registado.");

        if (!dto.getPassword().equals(dto.getConfirmarPassword()))
            throw new RuntimeException("As passwords não coincidem.");

        Utilizador u = new Utilizador();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());
        u.setPassword(passwordEncoder.encode(dto.getPassword()));
        u.setTelemovel(dto.getTelemovel());
        u.setGenero(dto.getGenero());
        u.setDataNascimento(dto.getDataNascimento());

        u.setRole(Role.USER);
        u.setEstado(true);
        u.setVerificado(false);
        u.setTentativasLogin(0);
        u.setTokenRecuperacao(UUID.randomUUID().toString());

        return utilizadorRepository.save(u);
    }


    public Utilizador autenticar(LoginDTO dto) {

        Utilizador u = utilizadorRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email não encontrado."));

        if (!u.getEstado())
            throw new RuntimeException("A conta está bloqueada.");

        if (!passwordEncoder.matches(dto.getPassword(), u.getPassword())) {

            int tentativas = u.getTentativasLogin() + 1;
            u.setTentativasLogin(tentativas);

            if (tentativas >= 5) {
                u.setEstado(false);
                utilizadorRepository.save(u);
                throw new RuntimeException("Conta bloqueada por tentativas incorretas.");
            }

            utilizadorRepository.save(u);
            throw new RuntimeException("Password incorreta. Tentativas restantes: " + (5 - tentativas));
        }

        u.setUltimoLogin(LocalDateTime.now());
        u.setTentativasLogin(0);
        utilizadorRepository.save(u);

        return u;
    }


    public String gerarTokenRecuperacao(String email) {

        Utilizador u = utilizadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));

        String token = UUID.randomUUID().toString();

        u.setTokenRecuperacao(token);
        utilizadorRepository.save(u);

        return token;
    }


    public String resetPassword(ResetPasswordDTO dto) {

        Utilizador u = utilizadorRepository.findByTokenRecuperacao(dto.getToken())
                .orElseThrow(() -> new RuntimeException("Token inválido ou expirado."));

        if (!dto.getNovaPassword().equals(dto.getConfirmarNovaPassword()))
            throw new RuntimeException("As passwords não coincidem.");

        u.setPassword(passwordEncoder.encode(dto.getNovaPassword()));
        u.setTokenRecuperacao(null);
        u.setTentativasLogin(0);
        u.setEstado(true);

        utilizadorRepository.save(u);

        return "Password redefinida com sucesso!";
    }
}



