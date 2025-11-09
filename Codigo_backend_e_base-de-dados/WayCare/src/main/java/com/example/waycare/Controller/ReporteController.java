package com.example.waycare.Controller;

import com.example.waycare.Service.ReporteService;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    // Criar novo reporte (associado a um utilizador e obstáculo)
    @PostMapping("/utilizador/{utiId}/obstaculo/{obsId}")
    public ResponseEntity<Reporte> criar(@PathVariable Long utiId, @PathVariable Long obsId, @RequestBody Reporte reporte) {
        return ResponseEntity.status(201).body(reporteService.criar(utiId, obsId, reporte));
    }

    // Lista todos os reportes
    @GetMapping
    public ResponseEntity<List<Reporte>> listarTodos() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }

    // Listar reportes por utilizador
    @GetMapping("/utilizador/{utiId}")
    public ResponseEntity<List<Reporte>> listarPorUtilizador(@PathVariable Long utiId) {
        return ResponseEntity.ok(reporteService.listarPorUtilizador(utiId));
    }

    // Procurar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Reporte> procurarPorId(@PathVariable Long id) {
        return reporteService.procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Atualizar estado (pendente, resolvido, etc.)
    @PutMapping("/{id}/estado")
    public ResponseEntity<Reporte> atualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String estado = body.get("estado");
        return ResponseEntity.ok(reporteService.atualizarEstado(id, estado));
    }



    // Apagar reporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/mapa")
    public ResponseEntity<List<Reporte>> listarParaMapa() {
        return ResponseEntity.ok(reporteService.listarTodos());
    }
    @GetMapping("/mapa/tipo/{tipo}")
    public ResponseEntity<List<Reporte>> listarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(reporteService.listarPorTipo(tipo));

    }
}
