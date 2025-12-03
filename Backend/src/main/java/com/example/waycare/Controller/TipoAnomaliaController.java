package com.example.waycare.Controller;

import com.example.waycare.Service.TipoAnomaliaService;
import com.example.waycare.models.TipoAnomalia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-anomalia")
public class TipoAnomaliaController {

    @Autowired
    private TipoAnomaliaService tipoAnomaliaService;

    // GET /api/tipos-anomalia
    @GetMapping
    public List<TipoAnomalia> listarTodos() {
        return tipoAnomaliaService.listarTodos();
    }

    // GET /api/tipos-anomalia/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TipoAnomalia> procurarPorId(@PathVariable Long id) {
        return tipoAnomaliaService.procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/tipos-anomalia
    @PostMapping
    public ResponseEntity<TipoAnomalia> criar(@RequestBody TipoAnomalia tipo) {
        TipoAnomalia novo = tipoAnomaliaService.criar(tipo);
        return ResponseEntity.ok(novo);
    }

    // PUT /api/tipos-anomalia/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TipoAnomalia> atualizar(@PathVariable Long id, @RequestBody TipoAnomalia novosDados) {
        try {
            TipoAnomalia atualizado = tipoAnomaliaService.atualizar(id, novosDados);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/tipos-anomalia/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tipoAnomaliaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/tipos-anomalia/search?nome=...
    @GetMapping("/search")
    public List<TipoAnomalia> procurarPorNome(@RequestParam String nome) {
        return tipoAnomaliaService.procurarPorNome(nome);
    }
}
