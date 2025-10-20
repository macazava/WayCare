package com.example.waycare.Controller;

import com.example.waycare.Service.LocalizacaoService;
import com.example.waycare.models.Localizacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/localizacoes")
@CrossOrigin(origins = "*")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public ResponseEntity<List<Localizacao>> listarTodos() {
        return ResponseEntity.ok(localizacaoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Localizacao>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(localizacaoService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Localizacao> criar(@RequestBody Localizacao localizacao) {
        Localizacao nova = localizacaoService.criar(localizacao);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Localizacao> atualizar(@PathVariable Long id, @RequestBody Localizacao localizacao) {
        try {
            Localizacao atualizada = localizacaoService.atualizar(id, localizacao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            localizacaoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

