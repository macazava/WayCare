package com.example.waycare.Controller;

import com.example.waycare.Service.FotografiaService;
import com.example.waycare.models.Fotografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fotografias")
@CrossOrigin(origins = "*")
public class FotografiaController {

    @Autowired
    private FotografiaService fotografiaService;

    @GetMapping
    public ResponseEntity<List<Fotografia>> listarTodos() {
        return ResponseEntity.ok(fotografiaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fotografia>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(fotografiaService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Fotografia> criar(@RequestBody Fotografia fotografia) {
        Fotografia nova = fotografiaService.criar(fotografia);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fotografia> atualizar(@PathVariable Long id, @RequestBody Fotografia fotografia) {
        try {
            Fotografia atualizada = fotografiaService.atualizar(id, fotografia);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            fotografiaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
