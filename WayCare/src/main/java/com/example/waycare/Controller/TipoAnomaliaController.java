package com.example.waycare.Controller;

import com.example.waycare.Service.TipoAnomaliaService;
import com.example.waycare.models.TipoAnomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/TipoAnomalia")
@CrossOrigin(origins = "*")
public class TipoAnomaliaController {

    @Autowired
    private TipoAnomaliaService tipoAnomaliaService;

    //Listar Tipos de anomalia

    @GetMapping
    public ResponseEntity<List<TipoAnomalia>> listarTodos() {
        return ResponseEntity.ok(tipoAnomaliaService.listarTodos());
    }

    //Procurar por tipo de anomalia

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<Optional<TipoAnomalia>> procurarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tipoAnomaliaService.procurarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Criar tipo de anomalia

    @PostMapping
    public ResponseEntity<TipoAnomalia> criar(@RequestBody TipoAnomalia tipoAnomalia) {
        TipoAnomalia nova = tipoAnomaliaService.criar(tipoAnomalia);
        return ResponseEntity.ok(nova);
    }

    //Atualizar tipo anomalia

    @PutMapping("/{id}")
    public ResponseEntity<TipoAnomalia> atualizar(@PathVariable Long id, @RequestBody TipoAnomalia tipoAnomalia) {
        try {
            TipoAnomalia atualizada = tipoAnomaliaService.atualizar(id, tipoAnomalia);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Eliaminar tipo anomalia

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            tipoAnomaliaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
