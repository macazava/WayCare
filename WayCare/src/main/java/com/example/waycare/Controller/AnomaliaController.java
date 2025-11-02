package com.example.waycare.Controller;

import com.example.waycare.Service.AnomaliaService;
import com.example.waycare.models.Anomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/anomalia")
@CrossOrigin(origins = "*")
public class AnomaliaController {

    @Autowired
    private AnomaliaService anomaliaService;

    //Listar todos as anomalias

    @GetMapping
    public ResponseEntity<List<Anomalia>> listarTodos() {
        return ResponseEntity.ok(anomaliaService.listarTodos());
    }

    //Procurar anomalia por id

    @GetMapping("procurar/{id}")
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
    //Atualizar Anomalia

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Anomalia> atualizar(@PathVariable Long id, @RequestBody Anomalia anomalia) {
        try {
            Anomalia atualizado = anomaliaService.atualizar(id, anomalia);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    //Eliminar Anomalia

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            anomaliaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
