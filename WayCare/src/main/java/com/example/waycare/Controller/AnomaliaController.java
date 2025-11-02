package com.example.waycare.Controller;

import com.example.waycare.Service.AnomaliaService;
import com.example.waycare.models.Anomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/obstaculos")
@CrossOrigin(origins = "*")
public class AnomaliaController {

    @Autowired
    private AnomaliaService anomaliaService;

    //Listar todos os obstaculos

    @GetMapping
    public ResponseEntity<List<Anomalia>> listarTodos() {
        return ResponseEntity.ok(anomaliaService.listarTodos());
    }

    //Procurar obstaculo por id

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Anomalia>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(anomaliaService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Anomalia> criar(@RequestBody Anomalia anomalia) {
        Anomalia novo = anomaliaService.criar(anomalia);
        return ResponseEntity.ok(novo);
    }
    //Atualizar obstaculo

    @PutMapping("/{id}")
    public ResponseEntity<Anomalia> atualizar(@PathVariable Long id, @RequestBody Anomalia anomalia) {
        try {
            Anomalia atualizado = anomaliaService.atualizar(id, anomalia);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //Eliminar obstaculo

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            anomaliaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
