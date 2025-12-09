package com.example.waycare.Controller;

import DTO.LoginDTO;
import DTO.RegisterDTO;
import DTO.ResetPasswordDTO;
import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/utilizadores")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public List<Utilizador> listarTodos() {
        return utilizadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> procurarPorId(@PathVariable Long id) {
        return utilizadorService.procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registar(@Valid @RequestBody RegisterDTO dto) {
        return ResponseEntity.ok(utilizadorService.registarNovoUtilizador(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO dto) {
        return ResponseEntity.ok(utilizadorService.autenticar(dto));
    }

    @PostMapping("/recuperar/{email}")
    public ResponseEntity<?> gerarToken(@PathVariable String email) {
        return ResponseEntity.ok(utilizadorService.gerarTokenRecuperacao(email));
    }

    @PostMapping("/resetar-password")
    public ResponseEntity<?> resetarPassword(@Valid @RequestBody ResetPasswordDTO dto) {
        return ResponseEntity.ok(utilizadorService.resetPassword(dto));
    }
}
