package com.example.waycare.Controller;

import com.example.waycare.Service.ObstaculoService;
import com.example.waycare.models.Obstaculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obstaculos")
@CrossOrigin(origins = "*")
public class ObstaculoController {

    @Autowired
    private ObstaculoService obstaculoService;

    @GetMapping
    public ResponseEntity<List<Obstaculo>> listarTodos() {
        return ResponseEntity.ok(obstaculoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Obstaculo>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(obstaculoService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Obstaculo> criar(@RequestBody Obstaculo obstaculo) {
        Obstaculo novo = obstaculoService.criar(obstaculo);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Obstaculo> atualizar(@PathVariable Long id, @RequestBody Obstaculo obstaculo) {
        try {
            Obstaculo atualizado = obstaculoService.atualizar(id, obstaculo);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            obstaculoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
