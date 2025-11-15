package com.example.waycare.Controller;

import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {
    private final Logger logger = LoggerFactory.getLogger(UtilizadorController.class);

    @Autowired
    private UtilizadorService utilizadorService;

    // Lista todos os utilizadores
    @GetMapping
    public ResponseEntity<List<Utilizador>> listarTodos() {
        return ResponseEntity.ok(utilizadorService.listarTodos());
    }

    // Procurar por ID
    @GetMapping("/procurar/{id}")
    public ResponseEntity<Utilizador> procurarPorId(@PathVariable Long id) {
        Optional<Utilizador> utilizador = utilizadorService.procurarPorId(id);
        return utilizador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar utilizador
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Utilizador> atualizar(@PathVariable Long id, @RequestBody Utilizador utilizador) {
        try {
            Utilizador atualizado = utilizadorService.atualizar(id, utilizador);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            logger.error("Erro ao atualizar utilizador com ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar utilizador
    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            utilizadorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Erro ao eliminar utilizador com ID {}: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    // Registar utilizador
    @PostMapping("/registar")
    public ResponseEntity<Utilizador> registar(@RequestBody Utilizador utilizador) {
        logger.info("Registando utilizador: {}", utilizador);
        try {
            Utilizador novo = utilizadorService.registar(utilizador);
            logger.info("Utilizador registado com sucesso: {}", novo);
            return ResponseEntity.status(HttpStatus.CREATED).body(novo);
        } catch (Exception e) {
            logger.error("Erro ao registar utilizador: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Utilizador loginRequest) {
        boolean autenticado = utilizadorService.autenticar(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (autenticado) {
            logger.info("Login efetuado com sucesso para email: {}", loginRequest.getEmail());
            return ResponseEntity.ok("Login efetuado com sucesso!");
        } else {
            logger.warn("Tentativa de login falhada para email: {}", loginRequest.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou password incorretos.");
        }
    }
}
