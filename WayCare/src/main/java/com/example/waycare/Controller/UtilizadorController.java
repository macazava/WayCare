package com.example.waycare.Controller;


import com.example.waycare.Service.UtilizadorService;
import com.example.waycare.models.Utilizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public ResponseEntity<List<Utilizador>> listarTodos() {
        return ResponseEntity.ok(utilizadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Utilizador>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(utilizadorService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Utilizador> criar(@RequestBody Utilizador utilizador) {
        Utilizador novo = utilizadorService.criar(utilizador);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> atualizar(@PathVariable Long id, @RequestBody Utilizador utilizador) {
        try {
            Utilizador atualizado = utilizadorService.atualizar(id, utilizador);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            utilizadorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



