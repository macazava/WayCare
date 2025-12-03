package com.example.waycare.Controller;


import com.example.waycare.Service.ReporteService;
import com.example.waycare.models.Reporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listarTodos() {
        return reporteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reporte> procurarPorId(@PathVariable Long id) {
        return reporteService.procurarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Reporte> criar(@RequestBody Reporte reporte) {
        return ResponseEntity.ok(reporteService.criar(reporte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> atualizar(@PathVariable Long id, @RequestBody Reporte novosDados) {
        return ResponseEntity.ok(reporteService.atualizar(id, novosDados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reporteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
